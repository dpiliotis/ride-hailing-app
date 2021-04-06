package com.dpiliotis.ride.hailing.app.mapper;

import com.dpiliotis.ride.hailing.app.dto.SurveyResponseDto;
import com.dpiliotis.ride.hailing.app.entity.SurveyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SurveyResponseMapper {

    private final AnswerMapper answerMapper;

    @Autowired
    public SurveyResponseMapper(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    public SurveyResponseDto convertToDto(SurveyResponse surveyResponse) {
        SurveyResponseDto dto = new SurveyResponseDto();

        dto.setId(surveyResponse.getId());
        dto.setAnswers(surveyResponse.getAnswers().stream().map(answerMapper::convertToDto).collect(Collectors.toList()));

        return dto;
    }

    public SurveyResponse convertToEntity(SurveyResponseDto surveyResponse) {
        SurveyResponse entity = new SurveyResponse();

        entity.setId(surveyResponse.getId());
        entity.setAnswers(surveyResponse.getAnswers().stream().map(answerMapper::convertToEntity).collect(Collectors.toList()));

        return entity;
    }
}
