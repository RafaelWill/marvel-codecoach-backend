package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.CreateSessionDto;
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

    private final PersonService personService;
    private final CoachingTopicService coachingTopicService;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    public SessionMapper(PersonService personService, CoachingTopicService coachingTopicService) {
        this.personService = personService;
        this.coachingTopicService = coachingTopicService;
    }

    public Session toEntity(CreateSessionDto createSessionDto) {
        return new Session(personService.getById(createSessionDto.getCoacheeId()),
                coachingTopicService.getById(createSessionDto.getTopic()),
                LocalDateTime.of(LocalDate.parse(createSessionDto.getDate(), formatter), LocalTime.parse(createSessionDto.getTime()) ),
                createSessionDto.getLocation(),
                createSessionDto.getRemarks());
    }
}
