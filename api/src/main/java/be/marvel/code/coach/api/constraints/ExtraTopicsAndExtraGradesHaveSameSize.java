package be.marvel.code.coach.api.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = {ExtraTopicsAndExtraGradesHaveSameSizeValidator.class})
public @interface ExtraTopicsAndExtraGradesHaveSameSize {
    String message() default
            "{be.marvel.code.coach.api.constraints.ExtraTopicsAndExtraGradesHaveSameSize.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
