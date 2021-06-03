package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.entity.Role;
import be.marvel.code.coach.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Person becomeCoach(List<CoachingTopic> topics, Person person) {
        Person personWithRoles = getById(person.getId());
        if (personWithRoles.hasRole(Role.COACH)) {
            throw new IllegalArgumentException("Person already is a coach");//TODO : change exception to a custom
        }
        personWithRoles.becomeCoach(topics);
        return personWithRoles;
    }

    @Override
    public List<Person> getAllCoaches() {
        return repository.findAllByUserCredentialRoles(Role.COACH);
    }

    @Override
    public Person getByEmail(String email) {
        return repository.findByUserCredential_Email(email);
    }
}
