package be.marvel.code.coach.domain.entity;

import be.marvel.code.coach.infrastructure.util.MailAddressValidator;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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

    @ElementCollection()
    @CollectionTable(name = "userrole", joinColumns = @JoinColumn(name = "usercredentialid"))
    @Column(name = "rolename")
    @Enumerated(EnumType.STRING)
    private Collection<Role> roles;

    public UserCredential() {
    }

    public UserCredential(String email, String password) {
        validateInput(email, password);
        this.email = email;
        this.password = password;
    }

    private void validateInput(String email, String password) {
        if (email == null || email.isBlank() || !MailAddressValidator.isMailAddressValid(email)) {
            throw new IllegalArgumentException("Provided email not valid.");
        }
        if (password == null || password.isBlank()) {
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

    public void addRole(Role role) {
        roles.add(role);
    }

    public Collection<Role> getRoles() {
        return Collections.unmodifiableCollection(roles);
    }
}
