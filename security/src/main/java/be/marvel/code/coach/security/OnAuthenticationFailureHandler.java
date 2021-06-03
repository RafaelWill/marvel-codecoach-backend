package be.marvel.code.coach.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class OnAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        switch (e.getClass().getSimpleName()) {
            /* if you want to send different feedback for CorrectEmailButWrongPassword
            case "CorrectEmailButWrongPasswordException":
                httpServletResponse.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
                break;*/
            case "CorrectEmailButWrongPasswordException":
            case "AuthenticationCredentialsNotFoundException":
            case "BadCredentialsException":
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                break;
            default:
                System.out.println("ERROR: " + e.getClass().getSimpleName());
                httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
}
