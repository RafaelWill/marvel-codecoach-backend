package be.marvel.code.coach.domain.repository;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    boolean existsByUserCredentialEmail(String email);
    List<Person> findAllByUserCredentialRoles(Role role);
    Person findByUserCredential_Email(String email);

}
