package be.marvel.code.coach.api.controller;

import be.marvel.code.coach.api.dto.CreateSessionDto;
import be.marvel.code.coach.api.dto.SessionDto;
import be.marvel.code.coach.api.mapper.SessionMapper;
import be.marvel.code.coach.service.service.CoachingTopicService;
import be.marvel.code.coach.service.service.EmailPrepareService;
import be.marvel.code.coach.service.service.PersonService;
import be.marvel.code.coach.service.service.SessionServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(path = "/" + SessionController.RESOURCE_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = {"http://localhost:4200", "https://codecoach-marvel.netlify.app"})
public class SessionController {
    public static final String RESOURCE_NAME = "sessions";
    private final SessionServiceImplementation sessionService;
    private final EmailPrepareService emailPrepareService;
    private final SessionMapper sessionMapper;
    private final PersonService personService;
    private final CoachingTopicService coachingTopicService;

    @Autowired
    public SessionController(SessionServiceImplementation sessionService, EmailPrepareService emailPrepareService, SessionMapper sessionMapper, PersonService personService, CoachingTopicService coachingTopicService) {
        this.sessionService = sessionService;
        this.emailPrepareService = emailPrepareService;
        this.sessionMapper = sessionMapper;
        this.personService = personService;
        this.coachingTopicService = coachingTopicService;
    }

    @PreAuthorize("hasAuthority('REQUEST_SESSION')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SessionDto createSession(@Valid @RequestBody CreateSessionDto createSessionDto) {
        var savedSession = sessionService.save(sessionMapper.toEntity(createSessionDto,
                personService.getById(createSessionDto.getCoacheeId()), coachingTopicService.getById(createSessionDto.getTopic())));
        var coach = savedSession.getCoach();

        emailPrepareService.sendMail(sessionMapper.getMailMapToSession(savedSession,savedSession.getCoachee().getFirstName()),
                savedSession.getCoachee().getEmail(), "Request a Session", "RequestSesion.html");

        emailPrepareService.sendMail(sessionMapper.getMailMapToSession(savedSession,coach.getFirstName()),
                coach.getEmail(), "There is a request for a coach session", "ReqestedSession.html");

        return sessionMapper.toDto(savedSession);
    }
}
