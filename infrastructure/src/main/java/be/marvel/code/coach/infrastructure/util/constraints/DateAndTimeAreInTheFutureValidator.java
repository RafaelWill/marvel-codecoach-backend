package be.marvel.code.coach.infrastructure.util.constraints;

import org.apache.commons.beanutils.PropertyUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAndTimeAreInTheFutureValidator implements ConstraintValidator<DateAndTimeAreInTheFuture,Object> {

    private String date;
    private String time;

    @Override
    public void initialize(DateAndTimeAreInTheFuture constraintAnnotation) {
        date = constraintAnnotation.date();
        time = constraintAnnotation.time();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            String dateAsString = (String) PropertyUtils.getProperty(object, date);
            String timeAsString = (String) PropertyUtils.getProperty(object, time);
            if (dateAsString == null || timeAsString == null) return true;
            LocalDateTime datetime = LocalDateTime.parse(dateAsString + " " + timeAsString, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return datetime.isAfter(LocalDateTime.now());
        } catch (DateTimeParseException ex){
            return true;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}
