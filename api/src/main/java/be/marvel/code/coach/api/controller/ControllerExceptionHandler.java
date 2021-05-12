package be.marvel.code.coach.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected void invalidLicencePlateHandler(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        log.error(ex.getMessage(),ex);
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }
}
