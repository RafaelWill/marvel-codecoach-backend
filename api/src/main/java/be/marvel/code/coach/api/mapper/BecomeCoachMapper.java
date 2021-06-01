package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.BecomeCoachDto;
import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public Map<String,String> getMailMapToCoach(Person coach){
        Map<String,String> result = new HashMap<>();
        result.put("firstname", coach.getFirstName());

        return result;
    }

    public Map<String,String> getMailMapToAdmin(Person coach,String motivation){
        Map<String,String> result = new HashMap<>();
        result.put("email", coach.getEmail());
        result.put("motivation", motivation);

        return result;
    }
}
