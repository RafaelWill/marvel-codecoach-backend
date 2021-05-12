package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.CreateUserCredentialDto;
import be.marvel.code.coach.domain.entity.UserCredential;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialMapper {

    public UserCredential toEntity(CreateUserCredentialDto dto){
        return new UserCredential(dto.getEmail(), dto.getPassword());
    }
}
