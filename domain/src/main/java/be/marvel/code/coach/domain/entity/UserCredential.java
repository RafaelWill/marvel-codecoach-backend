package be.marvel.code.coach.domain.entity;

import be.marvel.code.coach.infrastructure.util.MailAddressValidator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "usercredential")
public class UserCredential {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "usercredentialid")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public UserCredential() {
    }

    public UserCredential(String email, String password) {
        validateInput(email, password);
        this.email = email;
        this.password = password;
    }

    private void validateInput(String email, String password) {
        if ( email == null || email.isBlank() || !MailAddressValidator.isMailAddressValid(email)){
            throw new IllegalArgumentException("Provided email not valid.");
        }
        if (password == null || password.isBlank()){
            throw new IllegalArgumentException("Provided password not valid.");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
