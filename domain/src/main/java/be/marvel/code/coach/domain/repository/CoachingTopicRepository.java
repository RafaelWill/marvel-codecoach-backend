package be.marvel.code.coach.domain.repository;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoachingTopicRepository extends JpaRepository<CoachingTopic, UUID> {

}
