package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.entity.Role;
import be.marvel.code.coach.domain.entity.UserCredential;
import be.marvel.code.coach.domain.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplementationTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private EmailPrepareService emailPrepareService;
    @InjectMocks
    private PersonServiceImplementation personServiceImplementation;

    @Nested
    public class SavePerson {
        @Test
        void save_givenCorrectParameter_thenVerifyServiceCallsRepository() {
            Person mockPerson = mock(Person.class);
            when(personRepository.save(mockPerson)).thenReturn(mockPerson);
            personServiceImplementation.save(mockPerson);
            verify(personRepository).save(any());
        }

        @Test
        void save_givenCorrectParameter_thenReturnNewPerson() {
            //GIVEN
            Person mockPerson = mock(Person.class);
            when(personRepository.save(any())).thenReturn(mockPerson);
            //WHEN
            Person actualResult = personServiceImplementation.save(mockPerson);
            //THEN
            Assertions.assertThat(actualResult).isInstanceOf(Person.class);
        }
    }


    @Nested
    public class BecomeCoach {
        @Test
        void becomeCoach_givenInvalidList_thenThrowsIllegalArgumentException() {
            //given,
            List<CoachingTopic> emptyList = new ArrayList<>();
            UUID personId = UUID.randomUUID();

            //then
            Assertions.assertThatIllegalArgumentException().isThrownBy(() -> personServiceImplementation.becomeCoach(null, personId));
            Assertions.assertThatIllegalArgumentException().isThrownBy(() -> personServiceImplementation.becomeCoach(emptyList, personId));
        }

        @Test
        void becomeCoach_givenInvalidPersonId_thenThrowsIllegalArgumentException() {
            //given,
            List<CoachingTopic> list = new ArrayList<>();
            list.add(Mockito.mock(CoachingTopic.class));
            String motivation = "some text";
            UUID personId = null;

            //then
            Assertions.assertThatIllegalArgumentException().isThrownBy(() -> personServiceImplementation.becomeCoach(list, personId));
        }
//
//        @Test
//        void becomeCoach_givenValidInput_thenSendMail() {
//            //given
//            List<CoachingTopic> list = new ArrayList<>();
//            list.add(Mockito.mock(CoachingTopic.class));
//            String motivation = "some text";
//            UUID personId = UUID.randomUUID();
//            Person testPerson = Mockito.mock(Person.class);
//            when(testPerson.getUserCredential()).thenReturn(Mockito.mock(UserCredential.class));
//            when(personRepository.findById(personId)).thenReturn(Optional.of(testPerson));
//
//            //when
//            personServiceImplementation.becomeCoach(list, motivation, personId);
//
//            //then
////            verify(emailPrepareService, times(2)).sendSimpleEmail(any(), any(), any(), any());
//        }
    }

}
