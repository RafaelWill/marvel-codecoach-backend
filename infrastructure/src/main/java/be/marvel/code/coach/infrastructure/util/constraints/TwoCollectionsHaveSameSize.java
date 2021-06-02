package be.marvel.code.coach.infrastructure.util.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = {TwoCollectionsHaveSameSizeValidator.class})
public @interface TwoCollectionsHaveSameSize {

    String first();

    String second();

    String message() default
            "{be.marvel.code.coach.infrastructure.util.constraints.TwoCollectionsHaveSameSize.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
