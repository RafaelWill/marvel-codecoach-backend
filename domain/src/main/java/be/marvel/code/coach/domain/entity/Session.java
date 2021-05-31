package be.marvel.code.coach.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "sessionid")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coacheeid")
    private Person coachee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coachingtopicid")
    private CoachingTopic coachingtopic;

    @Column(name = "sessionmoment")
    private LocalDateTime sessionMoment;

    @Column(name = "location")
    private String location;

    @Column(name = "remarks")
    private String remarks;

    public Session(Person coachee, CoachingTopic coachingtopic, LocalDateTime sessionMoment, String location, String remarks) {
        this.coachee = coachee;
        this.coachingtopic = coachingtopic;
        this.sessionMoment = sessionMoment;
        this.location = location;
        this.remarks = remarks;
    }

    public Session() {
    }

    public UUID getId() {
        return id;
    }

    public Person getCoachee() {
        return coachee;
    }

    public CoachingTopic getCoachingtopic() {
        return coachingtopic;
    }

    public LocalDateTime getSessionMoment() {
        return sessionMoment;
    }

    public String getLocation() {
        return location;
    }

    public String getRemarks() {
        return remarks;
    }

    public Person getCoach(){
        return coachingtopic.getCoach();
    }
}
