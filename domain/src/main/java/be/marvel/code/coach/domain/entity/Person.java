package be.marvel.code.coach.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL,mappedBy = "coach")
    private List<CoachingTopic> topics;

    public Person() {
    }

    public Person(UserCredential userCredential, String firstName, String lastName) {
        this.userCredential = userCredential;
        this.firstName = firstName;
        this.lastName = lastName;
        topics = new ArrayList<>();
        userCredential.addRole(Role.COACHEE);
    }


    public void becomeCoach(List<CoachingTopic> topicsToAdd){
        topicsToAdd.forEach(this::addTopic);
        userCredential.addRole(Role.COACH);
    }

    public void addTopic(CoachingTopic topic) {
        topics.add(topic);
    }

    public boolean hasRole(Role role){
        return userCredential.hasRole(role);
    }


    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return userCredential.getEmail();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<CoachingTopic> getTopics() {
        return Collections.unmodifiableList(topics);
    }

    public List<Role> getRoles(){
        return List.copyOf(userCredential.getRoles());
    }

}
