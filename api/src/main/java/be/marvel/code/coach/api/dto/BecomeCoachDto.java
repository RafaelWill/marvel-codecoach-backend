package be.marvel.code.coach.api.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BecomeCoachDto {
    @NotBlank
    private String motivation;
    @NotBlank
    private String topic;
    @NotNull
    @Range(min = 1, max = 6)
    private Integer grade;
    @NotNull
    private List<String> extraTopics;// TODO custom validation for inner strings
    @NotNull
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
