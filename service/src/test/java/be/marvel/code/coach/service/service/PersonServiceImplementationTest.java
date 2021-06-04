package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.entity.Role;
import be.marvel.code.coach.domain.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplementationTest {

    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImplementation personServiceImplementation;

    @Nested
    public class GetAll {
        @Test
        void getAll_thenVerifyServiceCallsRepository() {
            //GIVEN
            List mockListOfPersons = mock(List.class);
            when(personRepository.findAll()).thenReturn(mockListOfPersons);
            //WHEN
            personServiceImplementation.getAll();
            //THEN
            verify(personRepository).findAll();
        }

        @Test
        void getAll_thenReturnListOfAllPersons() {
            //GIVEN
            List mockListOfPersons = mock(List.class);
            when(personRepository.findAll()).thenReturn(mockListOfPersons);
            //WHEN
            Collection<Person> actualResult = personServiceImplementation.getAll();
            //THEN
            Assertions.assertThat(actualResult).isInstanceOf(Collection.class);
        }

    }

    @Nested
    public class GetById {
        @Test
        void getById_givenCorrectParameter_thenVerifyServiceCallsRepository() {
            //GIVEN
            Person mockPerson = mock(Person.class);
            when(personRepository.findById(any())).thenReturn(Optional.of(mockPerson));
            //WHEN
            personServiceImplementation.getById(any());
            //THEN
            verify(personRepository).findById(any());
        }

        @Test
        void getById_givenCorrectParameter_thenReturnMatchingPerson() {
            //GIVEN
            Person mockPerson = mock(Person.class);
            when(personRepository.findById(any())).thenReturn(Optional.of(mockPerson));
            //WHEN
            Person actualResult = personServiceImplementation.getById(any());
            //THEN
            Assertions.assertThat(actualResult).isInstanceOf(Person.class);
        }

        @Test
        void getById_givenPersonDoesNotExist_thenThrowIllegalArgumentException() {
            //GIVEN
            when(personRepository.findById(any())).thenReturn(Optional.empty());
            //THEN
            Assertions.assertThatIllegalArgumentException().isThrownBy(()->personServiceImplementation.getById(any()));
        }
    }

    @Nested
    public class Save {
        @Test
        void save_givenCorrectParameter_thenVerifyServiceCallsRepository() {
            //GIVEN
            Person mockPerson = mock(Person.class);
            when(personRepository.save(any())).thenReturn(mockPerson);
            //WHEN
            personServiceImplementation.save(mockPerson);
            //THEN
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

        @Test
        void save_givenPersonEmailAlreadyExists_thenThrowIllegalArgumentException() {
            //GIVEN
            Person mockPerson = mock(Person.class);
            when(personRepository.existsByUserCredentialEmail(any())).thenReturn(true);
            //THEN
            Assertions.assertThatIllegalArgumentException().isThrownBy(()->personServiceImplementation.save(mockPerson));
        }
    }

    @Nested
    public class BecomeCoach {
        @Test
        void becomeCoach_givenCorrectParameters_thenVerifyPersistingPersonBecomesCoach() {
            //GIVEN
            Person mockPerson = mock(Person.class);
            var mockedList = mock(List.class);
            when(personRepository.findById(any())).thenReturn(Optional.of(mockPerson));
            //WHEN
            personServiceImplementation.becomeCoach(mockedList, mockPerson);
            //THEN
            InOrder inOrder = inOrder(mockPerson,personRepository);
            inOrder.verify(mockPerson).getId();
            inOrder.verify(personRepository).findById(any());
            inOrder.verify(mockPerson).becomeCoach(anyList());
        }

        @Test
        void becomeCoach_givenCorrectParameter_thenReturnCoach() {
            //GIVEN
            Person mockPerson = mock(Person.class);
            var mockedList = mock(List.class);
            when(personRepository.findById(any())).thenReturn(Optional.of(mockPerson));
            //WHEN
            Person actualResult = personServiceImplementation.becomeCoach(mockedList, mockPerson);
            //THEN
            Assertions.assertThat(actualResult).isInstanceOf(Person.class);
        }

        @Test
        void becomeCoach_givenPersonIsAlreadyCoach_thenThrowIllegalArgumentException() {
            //GIVEN
            Person mockPerson = mock(Person.class);
            var mockedList = mock(List.class);
            when(personRepository.findById(any())).thenReturn(Optional.of(mockPerson));
            when(mockPerson.hasRole(Role.COACH)).thenReturn(true);
            //THEN
            Assertions.assertThatIllegalArgumentException().isThrownBy(()->personServiceImplementation.becomeCoach(mockedList,mockPerson));
        }
    }

    @Nested
    public class GetAllCoaches {
        @Test
        void getAllCoaches_thenVerifyServiceCallsRepository() {
            //GIVEN
            List mockListOfPersons = mock(List.class);
            when(personRepository.findAllByUserCredentialRoles(any())).thenReturn(mockListOfPersons);
            //WHEN
            personServiceImplementation.getAllCoaches();
            //THEN
            verify(personRepository).findAllByUserCredentialRoles(any());
        }

        @Test
        void getAllCoaches_thenReturnListOfAllCoaches() {
            //GIVEN
            List mockListOfPersons = mock(List.class);
            when(personRepository.findAllByUserCredentialRoles(any())).thenReturn(mockListOfPersons);
            //WHEN
            Collection<Person> actualResult = personServiceImplementation.getAllCoaches();
            //THEN
            Assertions.assertThat(actualResult).isInstanceOf(Collection.class);
        }

    }

    @Nested
    public class GetByEmail {
        @Test
        void getByEmail_thenVerifyServiceCallsRepository() {
            //GIVEN
            Person mockPerson = mock(Person.class);
            when(personRepository.findByUserCredential_Email(any())).thenReturn(mockPerson);
            //WHEN
            personServiceImplementation.getByEmail(any());
            //THEN
            verify(personRepository).findByUserCredential_Email(any());
        }

        @Test
        void getByEmail_thenReturnMatchingPerson() {
            //GIVEN
            Person mockPerson = mock(Person.class);
            when(personRepository.findByUserCredential_Email(any())).thenReturn(mockPerson);
            //WHEN
            Person actualResult = personServiceImplementation.getByEmail(any());
            //THEN
            Assertions.assertThat(actualResult).isInstanceOf(Person.class);
        }

    }

}
