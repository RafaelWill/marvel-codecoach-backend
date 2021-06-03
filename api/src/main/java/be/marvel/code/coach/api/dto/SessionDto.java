package be.marvel.code.coach.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class SessionDto {

    private UUID id;
    private UUID coacheeId;
    private CoachingTopicDto coachingTopic;
    private LocalDateTime sessionMoment;
    private String location;
    private String remarks;

    public SessionDto() {
    }

    public UUID getId() {
        return id;
    }

    public UUID getCoacheeId() {
        return coacheeId;
    }

    public CoachingTopicDto getCoachingTopic() {
        return coachingTopic;
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

    public SessionDto setCoachee(UUID coacheeId) {
        this.coacheeId = coacheeId;
        return this;
    }

    public SessionDto setCoachingTopic(CoachingTopicDto coachingTopic) {
        this.coachingTopic = coachingTopic;
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
