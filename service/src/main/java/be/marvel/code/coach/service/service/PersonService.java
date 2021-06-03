package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface PersonService {
    Collection<Person> getAll();

    Person getById(UUID id);

    Person save(Person person);

    Person becomeCoach(List<CoachingTopic> topics, Person coach);

    List<Person> getAllCoaches();

    Person getByEmail(String email);

}
