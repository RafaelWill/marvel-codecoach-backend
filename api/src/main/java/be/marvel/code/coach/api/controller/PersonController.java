package be.marvel.code.coach.api.controller;

import be.marvel.code.coach.api.dto.CreatePersonDto;
import be.marvel.code.coach.api.dto.PersonDto;
import be.marvel.code.coach.api.mapper.PersonMapper;
import be.marvel.code.coach.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/" + PersonController.RESOURCE_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    public static final String RESOURCE_NAME = "users";
    private final PersonService personService;
    private final PersonMapper personMapper;

    @Autowired
    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto createPerson(@RequestBody CreatePersonDto createPersonDto) {
        return personMapper.toDto(personService.save(personMapper.toEntity(createPersonDto)));
    }

}