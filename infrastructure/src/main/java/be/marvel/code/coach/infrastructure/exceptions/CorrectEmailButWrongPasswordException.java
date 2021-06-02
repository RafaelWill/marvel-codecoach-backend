package be.marvel.code.coach.infrastructure.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CorrectEmailButWrongPasswordException extends AuthenticationException {
    public CorrectEmailButWrongPasswordException(String message) {super (message);}
}
