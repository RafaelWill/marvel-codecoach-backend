package be.marvel.code.coach.infrastructure.util.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Constraint(validatedBy = {TimeValidator.class})
public @interface Time {

    String message() default
            "{be.marvel.code.coach.infrastructure.util.constraints.Time.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
