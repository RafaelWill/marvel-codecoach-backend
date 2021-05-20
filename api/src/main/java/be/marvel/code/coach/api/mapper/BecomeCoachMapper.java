package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.BecomeCoachDto;
import be.marvel.code.coach.domain.entity.CoachingTopic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class BecomeCoachMapper {

    public List<CoachingTopic> toEntityList(BecomeCoachDto dto, UUID personId) {
        List<CoachingTopic> topicList = new ArrayList<>();
        topicList.add(new CoachingTopic(personId, dto.getTopic(), dto.getGrade()));

        for (int index = 0; index < dto.getExtraTopics().size(); index++) {
            topicList.add(new CoachingTopic(personId, dto.getExtraTopics().get(index), dto.getExtraGrades().get(index)));
        }

        return topicList;
    }
}
