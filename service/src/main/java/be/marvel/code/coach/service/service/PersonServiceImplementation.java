package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.entity.Role;
import be.marvel.code.coach.domain.repository.PersonRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PersonServiceImplementation implements PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonServiceImplementation(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Person> getAll() {
        return repository.findAll();
    }

    @Override
    public Person getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Person not found."));
    }

    @Override
    public Person save(Person person) {
        if(repository.existsByUserCredentialEmail(person.getUserCredential().getEmail())){ // TODO: feature envy -> replace with peron.getEmail()
            throw new IllegalArgumentException("Person could not be stored in the system");
        }
        person.getUserCredential().addRole(Role.COACHEE); // TODO: why isn't this added by default in the constructor? Try to push as much logic as possible in the entities.
        return repository.save(person);
    }

    @Override
    public Person becomeCoach(List<CoachingTopic> topics, String motivation, UUID personId) {
        //TODO: nothing is done with the motivation?
        validateInputBecomeCoach(topics, motivation, personId);

        Person person = getById(personId);
        // TODO: this could become a method in Person like becomeCoach(topics)
        topics.forEach(person::addTopic);
        person.getUserCredential().addRole(Role.COACH);

        return person;
    }

    @Override
    public List<Person> getAllCoaches() {
        return repository.findAllByUserCredentialRoles(Role.COACH);
    }

    private void validateInputBecomeCoach(List<CoachingTopic> topics, String motivation, UUID personId) {
        if (topics == null || topics.isEmpty()) {
            throw new IllegalArgumentException("No topics provided");
        }

        if (motivation == null || motivation.isBlank()) {
            throw new IllegalArgumentException("No motivation provided");
        }

        if (personId == null) {
            throw new IllegalArgumentException("No personId provided");
        }

        if(getById(personId).getUserCredential().getRoles().contains(Role.COACH)){
            throw new IllegalArgumentException("Person already is a coach");//TODO : change exception to a custom
        }
    }
}
