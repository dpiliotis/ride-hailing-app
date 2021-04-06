package com.dpiliotis.ride.hailing.app.mapper;

import com.dpiliotis.ride.hailing.app.dto.AnswerDto;
import com.dpiliotis.ride.hailing.app.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AnswerMapper {

    private final ChoiceMapper choiceMapper;
    private final QuestionMapper questionMapper;

    @Autowired
    public AnswerMapper(ChoiceMapper choiceMapper, QuestionMapper questionMapper) {
        this.choiceMapper = choiceMapper;
        this.questionMapper = questionMapper;
    }

    public AnswerDto convertToDto(Answer answer) {
        AnswerDto dto = new AnswerDto();

        dto.setId(answer.getId());
        dto.setQuestion(questionMapper.convertToDto(answer.getQuestion()));
        dto.setText(answer.getText());
        dto.setChoices(answer.getChoices().stream().map(choiceMapper::convertToDto).collect(Collectors.toList()));
        dto.setOther(answer.getOther());

        return dto;
    }

    public Answer convertToEntity(AnswerDto answer) {
        Answer entity = new Answer();

        entity.setId(answer.getId());
        entity.setQuestion(questionMapper.convertToEntity(answer.getQuestion()));
        entity.setText(answer.getText());
        entity.setChoices(answer.getChoices().stream().map(choiceMapper::convertToEntity).collect(Collectors.toList()));
        entity.setOther(answer.getOther());

        return entity;
    }
}
