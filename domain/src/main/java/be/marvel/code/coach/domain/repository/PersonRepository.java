package be.marvel.code.coach.domain.repository;

import be.marvel.code.coach.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
