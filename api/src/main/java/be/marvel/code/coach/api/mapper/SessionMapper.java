package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.CreateSessionDto;
import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.entity.Session;
import be.marvel.code.coach.service.service.CoachingTopicService;
import be.marvel.code.coach.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class SessionMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    public SessionMapper() {
    }

    public Session toEntity(CreateSessionDto createSessionDto, Person coachee, CoachingTopic topic) {
        return new Session(coachee,
                topic,
                LocalDateTime.of(LocalDate.parse(createSessionDto.getDate(), formatter), LocalTime.parse(createSessionDto.getTime()) ),
                createSessionDto.getLocation(),
                createSessionDto.getRemarks());
    }
}
