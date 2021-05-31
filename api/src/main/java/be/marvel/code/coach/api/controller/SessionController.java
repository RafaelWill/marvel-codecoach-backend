package be.marvel.code.coach.api.controller;

import be.marvel.code.coach.api.dto.CreateSessionDto;
import be.marvel.code.coach.api.mapper.SessionMapper;
import be.marvel.code.coach.service.service.EmailPrepareService;
import be.marvel.code.coach.service.service.PersonService;
import be.marvel.code.coach.service.service.SessionServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    @Autowired
    public SessionController(SessionServiceImplementation sessionService, EmailPrepareService emailPrepareService, SessionMapper sessionMapper, PersonService personService){
        this.sessionService = sessionService;
        this.emailPrepareService = emailPrepareService;
        this.sessionMapper = sessionMapper;
        this.personService = personService;
    }

    @PreAuthorize("hasAuthority('REQUEST_SESSION')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createSession123(@RequestBody CreateSessionDto createSessionDto) {
        var savedPerson = sessionService.save(sessionMapper.toEntity(createSessionDto));
        var coach = personService.getById(savedPerson.getCoachingtopic().getPersonid());
        emailPrepareService.sendSessionMail(savedPerson.getCoachee().getFirstName(), savedPerson.getCoachee().getUserCredential().getEmail(), savedPerson,"Request a Sesion", "RequestSesion.html");
        emailPrepareService.sendSessionMail(coach.getFirstName(), coach.getUserCredential().getEmail(), savedPerson,"There is a request for a coach session", "ReqestedSession.html");
    }
}
