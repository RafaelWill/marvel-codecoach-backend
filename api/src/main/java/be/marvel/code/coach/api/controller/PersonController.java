package be.marvel.code.coach.api.controller;

import be.marvel.code.coach.api.dto.BecomeCoachDto;
import be.marvel.code.coach.api.dto.CreatePersonDto;
import be.marvel.code.coach.api.dto.PersonDto;
import be.marvel.code.coach.api.mapper.BecomeCoachMapper;
import be.marvel.code.coach.api.mapper.PersonMapper;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.service.service.EmailPrepareService;
import be.marvel.code.coach.service.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public PersonController(PersonService personService, PersonMapper personMapper, BecomeCoachMapper becomeCoachMapper, EmailPrepareService emailPrepareService) {
        this.personService = personService;
        this.personMapper = personMapper;
        this.becomeCoachMapper = becomeCoachMapper;
        this.emailPrepareService = emailPrepareService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto createPerson(@RequestBody CreatePersonDto createPersonDto) {
        var savedPerson = personService.save(personMapper.toEntity(createPersonDto));
        emailPrepareService.sendSimpleEmail(savedPerson.getFirstName(), savedPerson.getEmail(), "Welcome", "welcome.html");
        return personMapper.toDto(savedPerson);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable UUID id) {
        return personMapper.toDto(personService.getById(id));
    }

    @PostMapping(path = "/{coachId}/become-coach")
    @ResponseStatus(HttpStatus.CREATED)
    public void becomeCoach(@PathVariable UUID coachId, @RequestBody BecomeCoachDto dto) {
        Person coach = personService.getById(coachId);
        var person = personService.becomeCoach(becomeCoachMapper.toEntityList(dto, coach), coachId);
        emailPrepareService.sendSimpleEmail(person.getFirstName(), person.getEmail(), "Become a coach", "becomeCoach.html");
        emailPrepareService.sendSimpleEmailAndMotivation(personService.getById(coachId).getEmail(), dto.getMotivation(), "marvelcodecoach@gmail.com", "Request to become a coach", "becomeCoachAndMotivation.html");
    }

    @GetMapping(path = "/coaches")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getOverViewCoaches(){
        return personMapper.toDtoList(personService.getAllCoaches());
    }
}
