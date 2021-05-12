package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.CreatePersonDto;
import be.marvel.code.coach.api.dto.PersonDto;
import be.marvel.code.coach.domain.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    private final UserCredentialMapper userCredentialMapper;

    public PersonMapper(UserCredentialMapper userCredentialMapper) {
        this.userCredentialMapper = userCredentialMapper;
    }

    public PersonDto toDto(Person entity) {
        return new PersonDto()
                .setId(entity.getId())
                .setEmail(entity.getUserCredential().getEmail())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName());
    }

    public Person toEntity(CreatePersonDto createPersonDto) {
        return new Person(
                userCredentialMapper.toEntity(createPersonDto.getUserCredential()),
                createPersonDto.getFirstName(),
                createPersonDto.getLastName());
    }
}
