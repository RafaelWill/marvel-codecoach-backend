package be.marvel.code.coach.api.controller;

import be.marvel.code.coach.api.dto.CreateSessionDto;
import be.marvel.code.coach.api.mapper.SessionMapper;
import be.marvel.code.coach.service.service.EmailPrepareService;
import be.marvel.code.coach.service.service.SessionServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "/" + SessionController.RESOURCE_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = {"http://localhost:4200", "https://codecoach-marvel.netlify.app"})
public class SessionController {
    public static final String RESOURCE_NAME = "sessions";
    private final SessionServiceImplementation sessionService;
    private final EmailPrepareService emailPrepareService;
    private final SessionMapper sessionMapper;

    @Autowired
    public SessionController(SessionServiceImplementation sessionService, EmailPrepareService emailPrepareService, SessionMapper sessionMapper){
        this.sessionService = sessionService;
        this.emailPrepareService = emailPrepareService;
        this.sessionMapper = sessionMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createSession(@RequestBody CreateSessionDto createSessionDto) {
        var savedPerson = sessionService.save(sessionMapper.toEntity(createSessionDto));
        //emailPrepareService.sendSimpleEmail(savedPerson.getFirstName(), savedPerson.getUserCredential().getEmail(), "Welcome", "welcome.html");
    }
}
