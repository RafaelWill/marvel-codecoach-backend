package be.marvel.code.coach.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class CreateSessionDto {
    private UUID topic;
    private UUID coacheeId;
    private String date;
    private String time;
    private String location;
    private String remarks;

    public CreateSessionDto() {
    }

    public UUID getTopic() {
        return topic;
    }

    public UUID getCoacheeId() {
        return coacheeId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getRemarks() {
        return remarks;
    }

    public CreateSessionDto setTopic(UUID topic) {
        this.topic = topic;
        return this;
    }

    public CreateSessionDto setCoacheeId(UUID coacheeId) {
        this.coacheeId = coacheeId;
        return this;
    }

    public CreateSessionDto setDate(String date) {
        this.date = date;
        return this;
    }

    public CreateSessionDto setTime(String time) {
        this.time = time;
        return this;
    }

    public CreateSessionDto setLocation(String location) {
        this.location = location;
        return this;
    }

    public CreateSessionDto setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }
}
