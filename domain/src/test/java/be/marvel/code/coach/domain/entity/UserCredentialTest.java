package be.marvel.code.coach.domain.entity;

import be.marvel.code.coach.domain.repository.PersonRepository;
import be.marvel.code.coach.infrastructure.util.MailAddressValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialTest {

    @Test
    void constructorUserCredential_givenInvalidEmailAddress_thenThrowsIllegalArgumentException() {
        //GIVEN
        String invalidEmail = "invalidmail.com";
        String blankEmail = "   ";
        String password = "AValidPassword";

        try (MockedStatic<MailAddressValidator> mailAddressValidator = Mockito.mockStatic(MailAddressValidator.class)) {
            mailAddressValidator.when(() -> MailAddressValidator.isMailAddressValid(invalidEmail)).thenReturn(false);
            mailAddressValidator.when(() -> MailAddressValidator.isMailAddressValid(blankEmail)).thenReturn(false);
            mailAddressValidator.when(() -> MailAddressValidator.isMailAddressValid(null)).thenReturn(false);
        }

        //THEN
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new UserCredential(invalidEmail, password));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new UserCredential(blankEmail, password));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new UserCredential(null, password));
    }

    @Test
    void constructorUserCredential_givenInvalidPassword_thenThrowsIllegalArgumentException() {
        //GIVEN
        String blankPassword = "   ";
        String email = "valid@mail.com";

        try (MockedStatic<MailAddressValidator> mailAddressValidator = Mockito.mockStatic(MailAddressValidator.class)) {
            mailAddressValidator.when(() -> MailAddressValidator.isMailAddressValid(email)).thenReturn(true);
        }

        //THEN
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new UserCredential(email, blankPassword));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new UserCredential(email, null));
    }
}
