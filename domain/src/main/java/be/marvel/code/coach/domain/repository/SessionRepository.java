package be.marvel.code.coach.domain.repository;

import be.marvel.code.coach.domain.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {

}
