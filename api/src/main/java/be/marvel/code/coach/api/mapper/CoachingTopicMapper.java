package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.CoachingTopicDto;
import be.marvel.code.coach.domain.entity.CoachingTopic;
import org.springframework.stereotype.Component;

@Component
public class CoachingTopicMapper {

    public CoachingTopicDto toDto(CoachingTopic coachingTopic) {
        return new CoachingTopicDto()
                .setId(coachingTopic.getId())
                .setPersonid(coachingTopic.getPersonid())
                .setTopic(coachingTopic.getTopic())
                .setRate(coachingTopic.getRate());
    }
}
