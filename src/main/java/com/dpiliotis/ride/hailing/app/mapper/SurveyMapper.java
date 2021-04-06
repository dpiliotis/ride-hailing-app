package com.dpiliotis.ride.hailing.app.mapper;

import com.dpiliotis.ride.hailing.app.dto.SurveyDto;
import com.dpiliotis.ride.hailing.app.entity.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SurveyMapper {

    private final QuestionMapper questionMapper;

    @Autowired
    public SurveyMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    public SurveyDto convertToDto(Survey survey) {
        SurveyDto dto = new SurveyDto();

        dto.setId(survey.getId());
        dto.setName(survey.getName());
        dto.setQuestions(survey.getQuestions().stream().map(questionMapper::convertToDto).collect(Collectors.toList()));

        return dto;
    }

    public Survey convertToEntity(SurveyDto survey) {
        Survey entity = new Survey();

        entity.setId(survey.getId());
        entity.setName(survey.getName());
        entity.setQuestions(survey.getQuestions().stream().map(questionMapper::convertToEntity).collect(Collectors.toList()));

        return entity;
    }
}
