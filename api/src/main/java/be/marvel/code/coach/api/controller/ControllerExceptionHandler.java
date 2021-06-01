package be.marvel.code.coach.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected void illegalArgumentExceptionHandler(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        log.error(ex.getMessage(), ex);
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        Map<String, String> errors = new TreeMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String name;
            if (error instanceof FieldError) {// these are the annotations on the fields
                name = ((FieldError) error).getField(); // name of the field
            } else { // these are the annotations on the class
                name = "this"; // TODO better name? object, self, ...
            }
            String errorMessage = error.getDefaultMessage();
            errors.put(name, errorMessage);
            log.error(name + ": " + errorMessage);
        });
        return this.handleExceptionInternal(ex, errors, headers, status, request);
    }
}
