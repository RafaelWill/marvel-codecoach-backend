package be.marvel.code.coach.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "personid")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usercredentialid")
    private UserCredential userCredential;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "personid")
    private List<CoachingTopic> topics;

    public Person() {
    }

    public Person(UserCredential userCredential, String firstName, String lastName) {
        validateInput(userCredential, firstName, lastName);
        this.userCredential = userCredential;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private void validateInput(UserCredential userCredential, String firstName, String lastName) {
        if (userCredential == null) {
            throw new IllegalArgumentException("Provided userCredential is invalid");
        }
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Provided firstName is invalid");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Provided lastName is invalid");
        }
    }

    public UUID getId() {
        return id;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<CoachingTopic> getTopics() {
        return topics;
    }

    public void addTopic(CoachingTopic topic) {
        topics.add(topic);
    }

    public List<CoachingTopic> getTopics() {
        return Collections.unmodifiableList(topics);
    }
}
