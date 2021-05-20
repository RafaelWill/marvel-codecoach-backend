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

    @Column(name = "personid")
    private UUID personid;

    @Column(name = "topic")
    private String topic;

    @Column(name = "rate")
    private int rate;

    public CoachingTopic() {
    }

    public CoachingTopic(UUID personid, String topic, int rate) {
        this.personid = personid;
        this.topic = topic;
        this.rate = rate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPersonid() {
        return personid;
    }

    public String getTopic() {
        return topic;
    }

    public int getRate() {
        return rate;
    }
}
