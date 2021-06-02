package be.marvel.code.coach.api.mapper;

import be.marvel.code.coach.api.dto.CreateUserCredentialDto;
import be.marvel.code.coach.domain.entity.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserCredentialMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserCredential toEntity(CreateUserCredentialDto dto){
        return new UserCredential(dto.getEmail(), passwordEncoder.encode(dto.getPassword()));
    }
}
