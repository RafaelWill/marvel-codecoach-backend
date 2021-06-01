package be.marvel.code.coach.domain.repository;

import be.marvel.code.coach.domain.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCredentialRepository extends JpaRepository<UserCredential, UUID>  {

    UserCredential findUserCredentialByEmail(String email);
}
