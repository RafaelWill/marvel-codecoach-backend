package be.marvel.code.coach.domain.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserCredentialTest {

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
