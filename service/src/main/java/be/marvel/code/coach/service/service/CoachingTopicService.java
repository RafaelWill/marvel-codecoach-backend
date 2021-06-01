package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.CoachingTopic;

import java.util.List;
import java.util.UUID;

public interface CoachingTopicService {
    CoachingTopic getById(UUID id);
    List<CoachingTopic> getAllTopicsFromCoach(UUID coachId);

}
