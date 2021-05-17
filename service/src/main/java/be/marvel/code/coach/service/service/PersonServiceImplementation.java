package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.repository.PersonRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLDataException;
import java.util.Collection;
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
    public Collection<Person> getAll(){
        return repository.findAll();
    }

    @Override
    public Person getById(UUID id){
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Person not found."));
    }

    @Override
    public Person save(Person person){
        try {
            return repository.save(person);
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("Person could not be stored in the system");
        }
    }
}
