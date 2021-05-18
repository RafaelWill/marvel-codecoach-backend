package be.marvel.code.coach.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonTest {

    @Test
    void constructorPerson_givenInvalidFirstName_thenThrowsIllegalArgumentException() {
        //GIVEN
        String lastName = "lastname";
        String blankFirstName = "   ";
        UserCredential userCredential = Mockito.mock(UserCredential.class);//mocked because its validation method calls external dependency/its an external dependency itself

        //THEN
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Person(userCredential, blankFirstName, lastName));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Person(userCredential, null, lastName));
    }

    @Test
    void constructorPerson_givenInvalidLastName_thenThrowsIllegalArgumentException() {
        //GIVEN
        String firstName = "firstname";
        String blankLastName = "   ";
        UserCredential userCredential = Mockito.mock(UserCredential.class);


        //THEN
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Person(userCredential, firstName, blankLastName));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Person(userCredential, firstName, null));
    }

    @Test
    void constructorPerson_givenNullUserCredential_thenThrowsIllegalArgumentException() {
        //GIVEN
        String firstName = "firstname";
        String lastName = "lastname";

        //THEN
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Person(null, firstName, lastName));
    }

}
