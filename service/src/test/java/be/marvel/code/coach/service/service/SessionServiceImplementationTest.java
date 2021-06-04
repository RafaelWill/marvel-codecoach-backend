package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.Session;
import be.marvel.code.coach.domain.repository.SessionRepository;
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
class SessionServiceImplementationTest {

    @Mock
    private SessionRepository repository;
    @InjectMocks
    private SessionServiceImplementation service;

    @Nested
    public class Save {
        @Test
        void save_thenVerifyServiceCallsRepository() {
            //WHEN
            service.save(any());
            //THEN
            verify(repository).save(any());
        }

        @Test
        void save_givenCorrectParameter_thenReturnNewSession() {
            //GIVEN
            Session mockSession = mock(Session.class);
            when(repository.save(any())).thenReturn(mockSession);
            //WHEN
            Session actualResult = service.save(any());
            //THEN
            Assertions.assertThat(actualResult).isInstanceOf(Session.class);
        }
    }

}
