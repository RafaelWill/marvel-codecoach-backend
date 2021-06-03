package be.marvel.code.coach.infrastructure.util.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeValidator implements ConstraintValidator<Time,String> {

    //TODO test
    @Override
    public boolean isValid(String time, ConstraintValidatorContext constraintValidatorContext) {
        if(time == null) return true;
        try{
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException ex){
            return false;
        }
    }
}
