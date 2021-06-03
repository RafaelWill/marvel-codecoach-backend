package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.CoachingTopicDto;
import be.marvel.code.coach.domain.entity.CoachingTopic;
import org.springframework.stereotype.Component;

@Component
public class CoachingTopicMapper {

    public CoachingTopicDto toDto(CoachingTopic coachingTopic) {
        return new CoachingTopicDto()
                .setId(coachingTopic.getId())
                .setCoachId(coachingTopic.getCoach().getId())
                .setTopic(coachingTopic.getTopic())
                .setGrade(coachingTopic.getGrade());
    }
}
