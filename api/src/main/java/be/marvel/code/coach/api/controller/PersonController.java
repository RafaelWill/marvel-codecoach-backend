package be.marvel.code.coach.api.controller;

import be.marvel.code.coach.api.dto.CreatePersonDto;
import be.marvel.code.coach.api.dto.PersonDto;
import be.marvel.code.coach.api.mapper.PersonMapper;
import be.marvel.code.coach.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @Autowired
    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto createPerson(@RequestBody CreatePersonDto createPersonDto){
        return personMapper.toDto(personService.save(personMapper.toEntity(createPersonDto)));
    }

}
