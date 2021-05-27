package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;

import java.util.UUID;

public interface CoachingTopicService {
    CoachingTopic getById(UUID id);
}
