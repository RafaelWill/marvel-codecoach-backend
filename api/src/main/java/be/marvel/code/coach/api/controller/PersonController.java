package be.marvel.code.coach.api.controller;

import be.marvel.code.coach.api.dto.BecomeCoachDto;
import be.marvel.code.coach.api.dto.CreatePersonDto;
import be.marvel.code.coach.api.dto.PersonDto;
import be.marvel.code.coach.api.mapper.BecomeCoachMapper;
import be.marvel.code.coach.api.mapper.PersonMapper;
import be.marvel.code.coach.api.mapper.mail.MailMapper;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.service.service.EmailPrepareService;
import be.marvel.code.coach.service.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(path = "/" + PersonController.RESOURCE_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = {"http://localhost:4200", "https://codecoach-marvel.netlify.app"})
public class PersonController {

    public static final String RESOURCE_NAME = "users";
    private final PersonService personService;
    private final PersonMapper personMapper;
    private final BecomeCoachMapper becomeCoachMapper;
    private final EmailPrepareService emailPrepareService;
    private final MailMapper mailMapper;

    @Autowired
    public PersonController(PersonService personService, PersonMapper personMapper, BecomeCoachMapper becomeCoachMapper, EmailPrepareService emailPrepareService, MailMapper mailMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
        this.becomeCoachMapper = becomeCoachMapper;
        this.emailPrepareService = emailPrepareService;
        this.mailMapper = mailMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto createPerson(@Valid @RequestBody CreatePersonDto createPersonDto) {
        var savedPerson = personService.save(personMapper.toEntity(createPersonDto));
        var returnValue = personMapper.toDto(savedPerson);

        emailPrepareService.sendMail(mailMapper.mapObject(returnValue), savedPerson.getEmail(), "Welcome", "welcome.html");

        return returnValue;
    }

    @PreAuthorize("hasAuthority('FIND_PERSON_BY_ID')")
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable UUID id) {
        return personMapper.toDto(personService.getById(id));
    }

    @PreAuthorize("hasAuthority('BECOME_COACH')")
    @PostMapping(path = "/{coacheeId}/become-coach")
    @ResponseStatus(HttpStatus.CREATED)
    public void becomeCoach(@PathVariable UUID coacheeId, @Valid @RequestBody BecomeCoachDto dto) {
        Person coachee = personService.getById(coacheeId);
        var coach = personService.becomeCoach(becomeCoachMapper.toEntityList(dto, coachee), coachee);

        emailPrepareService.sendMail(becomeCoachMapper.getMailMapToCoach(coach), coach.getEmail(), "Become a coach", "becomeCoach.html");
        emailPrepareService.sendMail(becomeCoachMapper.getMailMapToAdmin(coach,dto.getMotivation()), "marvelcodecoach@gmail.com", "Request to become a coach", "becomeCoachAndMotivation.html");
    }

    @PreAuthorize("hasAuthority('FIND_COACHES')")
    @GetMapping(path = "/coaches")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getOverViewCoaches(){
        return personMapper.toDtoList(personService.getAllCoaches());
    }
}
