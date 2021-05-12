package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplementationTest {

    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImplementation personServiceImplementation;

    @Test
    void save_givenCorrectParameter_thenVerifyServiceCallsRepository() {
        personServiceImplementation.save(any());
        verify(personRepository).save(any());
    }

    @Test
    void save_givenCorrectParameter_thenReturnNewPerson() {
        //GIVEN
        when(personRepository.save(any())).thenReturn(new Person(null, null, null));
        //WHEN
        Person actualResult = personServiceImplementation.save(any());
        //THEN
        Assertions.assertThat(actualResult).isInstanceOf(Person.class);
    }
}
