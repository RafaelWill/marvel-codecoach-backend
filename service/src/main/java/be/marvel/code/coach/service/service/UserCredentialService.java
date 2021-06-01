package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.UserCredential;

public interface UserCredentialService {
    UserCredential getUserByEmail(String email);

}
