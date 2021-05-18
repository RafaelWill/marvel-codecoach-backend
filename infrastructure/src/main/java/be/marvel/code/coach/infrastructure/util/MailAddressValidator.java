package be.marvel.code.coach.infrastructure.util;

import org.apache.commons.validator.routines.EmailValidator;

public final class MailAddressValidator {
    public static boolean isMailAddressValid(String mailAddress){
        return EmailValidator.getInstance().isValid(mailAddress);
    }
}
