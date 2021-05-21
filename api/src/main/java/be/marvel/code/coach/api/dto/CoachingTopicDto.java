package be.marvel.code.coach.api.dto;

import java.util.UUID;

public class CoachingTopicDto {

    private UUID id;
    private UUID personid;
    private String topic;
    private int rate;

    public CoachingTopicDto() {
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

    public CoachingTopicDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public CoachingTopicDto setPersonid(UUID personid) {
        this.personid = personid;
        return this;
    }

    public CoachingTopicDto setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public CoachingTopicDto setRate(int rate) {
        this.rate = rate;
        return this;
    }
}
