package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.UserCredential;
import be.marvel.code.coach.domain.repository.UserCredentialRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserCredentialServiceImplementation implements UserCredentialService {

    private final UserCredentialRepository userCredentialRepository;

    public UserCredentialServiceImplementation(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    @Override
    public UserCredential getUserByEmail(String email) {
        return userCredentialRepository.findUserCredentialByEmail(email);
    }
}
