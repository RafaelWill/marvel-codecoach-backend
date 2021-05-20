package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.entity.Role;
import be.marvel.code.coach.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PersonServiceImplementation implements PersonService {

    private final PersonRepository repository;
    private final EmailPrepareService emailPrepareService;

    @Autowired
    public PersonServiceImplementation(PersonRepository repository, EmailPrepareService emailPrepareService) {
        this.repository = repository;
        this.emailPrepareService = emailPrepareService;
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
        try {
            return repository.save(person);
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("Person could not be stored in the system");
        }
    }

    @Override
    public Person becomeCoach(List<CoachingTopic> topics, String motivation, UUID personId) {
        Person person = getById(personId);
        topics.forEach(person::addTopic);
        person.getUserCredential().addRole(Role.COACH);

        emailPrepareService.sendSimpleEmail(person.getFirstName(), person.getUserCredential().getEmail(), "Become a coach", "becomeCoach.html");
        emailPrepareService.sendSimpleEmail("admin who cares", "marvelcodecoach@gmail.com", "Request to become a coach", "becomeCoach.html");
        return person;
    }
}
