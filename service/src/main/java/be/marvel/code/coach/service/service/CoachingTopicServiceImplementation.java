package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.repository.CoachingTopicRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CoachingTopicServiceImplementation implements CoachingTopicService{

    private final CoachingTopicRepository coachingTopicRepository;

    public CoachingTopicServiceImplementation(CoachingTopicRepository coachingTopicRepository) {
        this.coachingTopicRepository = coachingTopicRepository;
    }

    @Override
    public CoachingTopic getById(UUID id) {
        return coachingTopicRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Topic not found"));
    }

    @Override
    public List<CoachingTopic> getAllTopicsFromCoach(UUID coachId) {
        return coachingTopicRepository.findAllByCoach_Id(coachId);
    }
}
