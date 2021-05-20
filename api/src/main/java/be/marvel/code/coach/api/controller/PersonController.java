package be.marvel.code.coach.api.controller;

import be.marvel.code.coach.api.dto.BecomeCoachDto;
import be.marvel.code.coach.api.dto.CreatePersonDto;
import be.marvel.code.coach.api.dto.PersonDto;
import be.marvel.code.coach.api.mapper.BecomeCoachMapper;
import be.marvel.code.coach.api.mapper.PersonMapper;
import be.marvel.code.coach.service.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public PersonController(PersonService personService, PersonMapper personMapper, BecomeCoachMapper becomeCoachMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
        this.becomeCoachMapper = becomeCoachMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto createPerson(@RequestBody CreatePersonDto createPersonDto) {
        return personMapper.toDto(personService.save(personMapper.toEntity(createPersonDto)));
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable UUID id) {
        var x = personService.getById(id);//TODO remove me
        return personMapper.toDto(personService.getById(id));
    }

    @PostMapping(path = "/{id}/become-coach")
    @ResponseStatus(HttpStatus.CREATED)
    public void becomeCoach(@PathVariable UUID id, @RequestBody BecomeCoachDto dto) {
        personService.becomeCoach(becomeCoachMapper.toEntityList(dto, id), dto.getMotivation(), id);
    }
}
