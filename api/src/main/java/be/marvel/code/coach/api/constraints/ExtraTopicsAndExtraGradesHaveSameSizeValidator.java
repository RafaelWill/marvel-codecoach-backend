package be.marvel.code.coach.api.constraints;

import be.marvel.code.coach.api.dto.BecomeCoachDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExtraTopicsAndExtraGradesHaveSameSizeValidator implements ConstraintValidator<ExtraTopicsAndExtraGradesHaveSameSize, BecomeCoachDto> {

    //TODO test
    @Override
    public boolean isValid(BecomeCoachDto becomeCoachDto, ConstraintValidatorContext constraintValidatorContext) {
        return becomeCoachDto.getExtraTopics().size() == becomeCoachDto.getExtraGrades().size();
    }
}
