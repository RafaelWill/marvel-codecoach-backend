package be.marvel.code.coach.domain.entity;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonTest {


    @Nested
    class Constructor{
        @Test
        void constructor_givenValidParameters_thenAddCoachRoleToUserCredential() {
            //GIVEN
            UserCredential mockUserCredential = mock(UserCredential.class);
            //WHEN
            new Person(mockUserCredential,"","");
            //THEN
            verify(mockUserCredential).addRole(Role.COACHEE);
        }
    }

    @Nested
    class AddTopic{
        @Test
        void addTopic_givenCoachingTopic_thenItIsAdded() {
            //GIVEN
            Person person = new Person(mock(UserCredential.class),"","");
            CoachingTopic mockTopic = mock(CoachingTopic.class);
            //WHEN
            person.addTopic(mockTopic);
            //THEN
            assertThat(person.getTopics()).containsExactly(mockTopic);
        }
    }

    @Nested
    class BecomeCoach{

        @Test
        void becomeCoach_givenListOfCoachingTopics_thenAddsListOfTopicsToTopics() {
            //GIVEN
            Person person = new Person(mock(UserCredential.class),"","");
            List<CoachingTopic> mockTopics = List.of(mock(CoachingTopic.class),mock(CoachingTopic.class),mock(CoachingTopic.class));
            //WHEN
            person.becomeCoach(mockTopics);
            //THEN
            assertThat(person.getTopics()).hasSize(3);
        }

        @Test
        void becomeCoach_thenAddsRoleCoach() {
            //GIVEN
            UserCredential mockUserCredential = mock(UserCredential.class);
            Person person = new Person(mockUserCredential,"","");
            List<CoachingTopic> mockTopics = List.of(mock(CoachingTopic.class),mock(CoachingTopic.class),mock(CoachingTopic.class));
            //WHEN
            person.becomeCoach(mockTopics);
            //THEN
            verify(mockUserCredential).addRole(Role.COACH);
        }
    }

    @Nested
    class HasRole{
        @Test
        void hasRole_thenVerifyUserCredentialHasRoleIsUsed() {
            //GIVEN
            UserCredential mockUserCredential = mock(UserCredential.class);
            Person person = new Person(mockUserCredential,"","");
            //WHEN
            person.hasRole(any());
            //THEN
            verify(mockUserCredential).hasRole(any());
        }
    }




}
