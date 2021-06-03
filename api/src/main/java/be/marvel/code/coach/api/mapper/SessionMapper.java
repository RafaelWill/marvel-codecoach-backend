package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.CreateSessionDto;
import be.marvel.code.coach.api.dto.SessionDto;
import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessionMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final CoachingTopicMapper coachingTopicMapper;

    @Autowired
    public SessionMapper(CoachingTopicMapper coachingTopicMapper) {
        this.coachingTopicMapper = coachingTopicMapper;
    }

    public Session toEntity(CreateSessionDto createSessionDto, Person coachee, CoachingTopic topic) {
        return new Session(coachee,
                topic,
                LocalDateTime.of(LocalDate.parse(createSessionDto.getDate(), formatter), LocalTime.parse(createSessionDto.getTime()) ),
                createSessionDto.getLocation(),
                createSessionDto.getRemarks());
    }

    public Map<String,String> getMailMapToSession(Session session, String name){
        Map<String,String> result = new HashMap<>();
        result.put("name", name);
        result.put("topic", session.getCoachingtopic().getTopic());
        result.put("sessionMoment", session.getSessionMoment().toString());
        result.put("location", session.getLocation());

        return result;
    }

    public SessionDto toDto(Session session) {
        return new SessionDto()
                .setId(session.getId())
                .setCoachee(session.getCoachee().getId())
                .setCoachingTopic(coachingTopicMapper.toDto(session.getCoachingtopic()))
                .setSessionMoment(session.getSessionMoment())
                .setLocation(session.getLocation())
                .setRemarks(session.getRemarks());
    }
}
