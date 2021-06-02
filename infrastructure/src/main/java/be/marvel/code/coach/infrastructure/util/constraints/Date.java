package be.marvel.code.coach.infrastructure.util.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Constraint(validatedBy = {DateValidator.class})
public @interface Date {

    String message() default
            "{be.marvel.code.coach.infrastructure.util.constraints.Date.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
