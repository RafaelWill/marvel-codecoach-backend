package be.marvel.code.coach.infrastructure.util.constraints;

import org.apache.commons.beanutils.PropertyUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class TwoCollectionsHaveSameSizeValidator implements ConstraintValidator<TwoCollectionsHaveSameSize, Object> {

    private String firstName;
    private String secondName;

    @Override
    public void initialize(TwoCollectionsHaveSameSize constraintAnnotation) {
        firstName = constraintAnnotation.first();
        secondName = constraintAnnotation.second();
    }

    //TODO test
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Collection firstCollection = (Collection) PropertyUtils.getProperty(object, firstName);
            Collection secondCollection = (Collection) PropertyUtils.getProperty(object, secondName);
            if (firstCollection == null || secondCollection == null) return true;
            return firstCollection.size() == secondCollection.size();

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

}
