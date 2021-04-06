package com.dpiliotis.ride.hailing.app.mapper;

import com.dpiliotis.ride.hailing.app.dto.QuestionDto;
import com.dpiliotis.ride.hailing.app.entity.Question;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final ChoiceMapper choiceMapper;

    public QuestionMapper(ChoiceMapper choiceMapper) {
        this.choiceMapper = choiceMapper;
    }

    public QuestionDto convertToDto(Question question) {
        QuestionDto dto = new QuestionDto();

        dto.setId(question.getId());
        dto.setDescription(question.getDescription());
        dto.setType(question.getType());
        dto.setChoices(question.getChoices().stream().map(choiceMapper::convertToDto).collect(Collectors.toList()));

        return dto;
    }

    public Question convertToEntity(QuestionDto question) {
        Question entity = new Question();

        entity.setId(question.getId());
        entity.setDescription(question.getDescription());
        entity.setType(question.getType());
        entity.setChoices(question.getChoices().stream().map(choiceMapper::convertToEntity).collect(Collectors.toList()));

        return entity;
    }
}
