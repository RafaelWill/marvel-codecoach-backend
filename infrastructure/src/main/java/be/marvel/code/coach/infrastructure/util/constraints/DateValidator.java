package be.marvel.code.coach.infrastructure.util.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<Date,String> {

    //TODO test
    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if(date == null) return true;
        try{
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        } catch (DateTimeParseException ex){
            return false;
        }
    }
}
