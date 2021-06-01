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
        if (repository.existsByUserCredentialEmail(person.getEmail())) {
            throw new IllegalArgumentException("Person could not be stored in the system");
        }
        return repository.save(person);
    }

    @Override
    public Person becomeCoach(List<CoachingTopic> topics, UUID personId) {
        validateInputBecomeCoach(topics, personId);

        Person person = getById(personId);
        person.becomeCoach(topics);

        return person;
    }

    @Override
    public List<Person> getAllCoaches() {
        return repository.findAllByUserCredentialRoles(Role.COACH);
    }

    @Override
    public Person getByEmail(String email) {
        return repository.findByUserCredential_Email(email);
    }

    private void validateInputBecomeCoach(List<CoachingTopic> topics, UUID personId) {

        if (topics == null || topics.isEmpty()) {
            throw new IllegalArgumentException("No topics provided");
        }

        if (personId == null) {
            throw new IllegalArgumentException("No personId provided");
        }

        if (getById(personId).hasRole(Role.COACH)) {
            throw new IllegalArgumentException("Person already is a coach");//TODO : change exception to a custom
        }
    }
}
