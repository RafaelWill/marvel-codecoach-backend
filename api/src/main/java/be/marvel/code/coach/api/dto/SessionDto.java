package be.marvel.code.coach.api.dto;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;

import java.time.LocalDateTime;
import java.util.UUID;

public class SessionDto {

    private UUID id;
    private Person coachee;
    private CoachingTopic coachingtopic;
    private LocalDateTime sessionMoment;
    private String location;
    private String remarks;

    public SessionDto() {
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

    public SessionDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public SessionDto setCoachee(Person coachee) {
        this.coachee = coachee;
        return this;
    }

    public SessionDto setCoachingtopic(CoachingTopic coachingtopic) {
        this.coachingtopic = coachingtopic;
        return this;
    }

    public SessionDto setSessionMoment(LocalDateTime sessionMoment) {
        this.sessionMoment = sessionMoment;
        return this;
    }

    public SessionDto setLocation(String location) {
        this.location = location;
        return this;
    }

    public SessionDto setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }
}
