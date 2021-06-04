package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.UserCredential;
import be.marvel.code.coach.domain.repository.UserCredentialRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCredentialServiceImplementationTest {


    @Mock
    private UserCredentialRepository repository;
    @InjectMocks
    private UserCredentialServiceImplementation service;

    @Nested
    public class GetUserByEmail {
        @Test
        void getUserByEmail_thenVerifyServiceCallsRepository() {
            //GIVEN
            UserCredential mockUserCredential = mock(UserCredential.class);
            when(repository.findUserCredentialByEmail(any())).thenReturn(mockUserCredential);
            //WHEN
            service.getUserByEmail(any());
            //THEN
            verify(repository).findUserCredentialByEmail(any());
        }

        @Test
        void getUserByEmail_thenReturnMatchingUserCredential() {
            //GIVEN
            UserCredential mockUserCredential = mock(UserCredential.class);
            when(repository.findUserCredentialByEmail(any())).thenReturn(mockUserCredential);
            //WHEN
            UserCredential actualResult = service.getUserByEmail(any());
            //THEN
            Assertions.assertThat(actualResult).isInstanceOf(UserCredential.class);
        }

    }

}
