package be.marvel.code.coach.api.dto;

import java.util.Collection;
import java.util.List;

public class BecomeCoachDto {
    private String motivation;
    private String topic;
    private Integer grade;
    private List<String> extraTopics;
    private List<Integer> extraGrades;

    public BecomeCoachDto() {
    }

    public String getMotivation() {
        return motivation;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getGrade() {
        return grade;
    }

    public List<String> getExtraTopics() {
        return extraTopics;
    }

    public List<Integer> getExtraGrades() {
        return extraGrades;
    }

    public BecomeCoachDto setMotivation(String motivation) {
        this.motivation = motivation;
        return this;
    }

    public BecomeCoachDto setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public BecomeCoachDto setGrade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public BecomeCoachDto setExtraTopics(List<String> extraTopics) {
        this.extraTopics = extraTopics;
        return this;
    }

    public BecomeCoachDto setExtraGrades(List<Integer> extraGrades) {
        this.extraGrades = extraGrades;
        return this;
    }
}
