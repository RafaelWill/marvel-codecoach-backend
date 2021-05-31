package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.BecomeCoachDto;
import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class BecomeCoachMapper {

    public List<CoachingTopic> toEntityList(BecomeCoachDto dto, Person coach) {
        List<CoachingTopic> topicList = new ArrayList<>();
        topicList.add(new CoachingTopic(coach, dto.getTopic(), dto.getGrade()));

        for (int index = 0; index < dto.getExtraTopics().size(); index++) {
            topicList.add(new CoachingTopic(coach, dto.getExtraTopics().get(index), dto.getExtraGrades().get(index)));
        }

        return topicList;
    }
}
