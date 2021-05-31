package be.marvel.code.coach.api.controller;

import be.marvel.code.coach.api.dto.CreateSessionDto;
import be.marvel.code.coach.service.service.EmailPrepareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "/" + TestController.RESOURCE_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = {"http://localhost:4200", "https://codecoach-marvel.netlify.app"})
public class TestController {
    public static final String RESOURCE_NAME = "test";
    private final EmailPrepareService emailPrepareService;

    @Autowired
    public TestController(EmailPrepareService emailPrepareService) {
        this.emailPrepareService = emailPrepareService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public void createSession2() {
        emailPrepareService.sendSimpleEmail("Miguel", "marvelcodecoach@gmail.com", "Welcome", "welcome.html");

        emailPrepareService.sendSimpleEmail("Miguel", "marvelcodecoach@gmail.com", "Become a coach", "becomeCoach.html");
        emailPrepareService.sendSimpleEmailAndMotivation("miguel.maieur@hotmail.com", "Motivation of the coachee who becomes a coach", "miguel.maieur@hotmail.com", "Request to become a coach", "becomeCoachAndMotivation.html");
    }
}
