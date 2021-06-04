package be.marvel.code.coach.domain.entity;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserCredentialTest {

    @Nested
    class AddRole{
        @Test
        void addRole_givenNewRole_thenNewRoleIsAdded() {
            //GIVEN
            UserCredential userCredential = new UserCredential("","");
            //WHEN
            userCredential.addRole(Role.COACHEE);
            //THEN
            assertThat(userCredential.getRoles()).containsExactly(Role.COACHEE);
        }

        @Test
        void addRole_givenPreexistingRole_thenRoleIsNotAdded() {
            //GIVEN
            UserCredential userCredential = new UserCredential("","");
            //WHEN
            userCredential.addRole(Role.COACHEE);
            userCredential.addRole(Role.COACHEE);
            //THEN
            assertThat(userCredential.getRoles()).containsExactly(Role.COACHEE);
        }
    }

    @Nested
    class HasRole{

        @Test
        void hasRole_whenRoleInRoles_thenTrue() {
            //GIVEN
            UserCredential userCredential = new UserCredential("","");
            userCredential.addRole(Role.COACHEE);
            //WHEN
            boolean actualResult = userCredential.hasRole(Role.COACHEE);
            //THEN
            assertThat(actualResult).isTrue();
        }

        @Test
        void hasRole_whenRoleNotInRoles_thenFalse() {
            //GIVEN
            UserCredential userCredential = new UserCredential("","");
            //WHEN
            boolean actualResult = userCredential.hasRole(Role.COACHEE);
            //THEN
            assertThat(actualResult).isFalse();
        }
    }
}
