package be.marvel.code.coach.domain.entity;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
       //TODO
    }

    @Nested
    class HasRole{
        @Test
        void hasRole_givenRoleThatHasBeenAdded_thenTrue() {
        //TODO
        }
    }




}
