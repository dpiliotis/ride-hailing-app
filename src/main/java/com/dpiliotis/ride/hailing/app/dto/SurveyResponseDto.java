package com.dpiliotis.ride.hailing.app.dto;

import java.util.List;

public class SurveyResponseDto {

    private Long id;
    private List<AnswerDto> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
