package be.marvel.code.coach.api.dto;

import java.util.UUID;

public class CoachingTopicDto {

    private UUID id;
    private UUID coachId;
    private String topic;
    private int grade;

    public CoachingTopicDto() {
    }

    public UUID getId() {
        return id;
    }

    public UUID getCoachId() {
        return coachId;
    }

    public String getTopic() {
        return topic;
    }

    public int getGrade() {
        return grade;
    }

    public CoachingTopicDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public CoachingTopicDto setCoachId(UUID coachId) {
        this.coachId = coachId;
        return this;
    }

    public CoachingTopicDto setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public CoachingTopicDto setGrade(int grade) {
        this.grade = grade;
        return this;
    }
}
