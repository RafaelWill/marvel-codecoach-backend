package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.CreatePersonDto;
import be.marvel.code.coach.api.dto.PersonDto;
import be.marvel.code.coach.domain.entity.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    private final UserCredentialMapper userCredentialMapper;
    private final CoachingTopicMapper coachingTopicMapper;

    public PersonMapper(UserCredentialMapper userCredentialMapper, CoachingTopicMapper coachingTopicMapper) {
        this.userCredentialMapper = userCredentialMapper;
        this.coachingTopicMapper = coachingTopicMapper;
    }

    public PersonDto toDto(Person entity) {
        return new PersonDto()
                .setId(entity.getId())
                .setEmail(entity.getEmail())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setCoachingTopics(entity.getTopics().stream().map(coachingTopicMapper::toDto).collect(Collectors.toList()))
                .setRoles(entity.getRoles().stream().map(Enum::name).collect(Collectors.toList()));
    }

    public Person toEntity(CreatePersonDto createPersonDto) {
        return new Person(
                userCredentialMapper.toEntity(createPersonDto.getUserCredential()),
                createPersonDto.getFirstName(),
                createPersonDto.getLastName());
    }

    public List<PersonDto> toDtoList(List<Person> entityList){
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
