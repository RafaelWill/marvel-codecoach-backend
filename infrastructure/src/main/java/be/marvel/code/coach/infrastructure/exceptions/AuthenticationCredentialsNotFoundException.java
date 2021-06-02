package be.marvel.code.coach.infrastructure.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationCredentialsNotFoundException extends AuthenticationException {
public AuthenticationCredentialsNotFoundException(String message) {super(message);}
}
