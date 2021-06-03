package be.marvel.code.coach.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "coachingtopic")
public class CoachingTopic {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "coachingtopicid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "personid")
    private Person coach;

    @Column(name = "topic")
    private String topic;

    @Column(name = "grade")
    private int grade;

    public CoachingTopic() {
    }

    public CoachingTopic(Person coach, String topic, int grade) {
        this.coach = coach;
        this.topic = topic;
        this.grade = grade;
    }

    public UUID getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public int getGrade() {
        return grade;
    }

    public Person getCoach(){
        return coach;
    }
}
