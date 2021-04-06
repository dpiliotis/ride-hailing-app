package com.dpiliotis.ride.hailing.app.dto;

import com.dpiliotis.ride.hailing.app.entity.QuestionType;

import java.util.List;

public class QuestionDto {

    private Long id;
    private String description;
    private QuestionType type;
    private List<ChoiceDto> choices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public List<ChoiceDto> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDto> choices) {
        this.choices = choices;
    }
}
