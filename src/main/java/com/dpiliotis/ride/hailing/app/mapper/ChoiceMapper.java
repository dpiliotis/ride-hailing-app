package com.dpiliotis.ride.hailing.app.mapper;

import com.dpiliotis.ride.hailing.app.dto.ChoiceDto;
import com.dpiliotis.ride.hailing.app.entity.Choice;
import org.springframework.stereotype.Component;

@Component
public class ChoiceMapper {

    public ChoiceDto convertToDto(Choice choice) {
        ChoiceDto dto = new ChoiceDto();

        dto.setId(choice.getId());
        dto.setDescription(choice.getDescription());

        return dto;
    }

    public Choice convertToEntity(ChoiceDto choice) {
        Choice entity = new Choice();

        entity.setId(choice.getId());
        entity.setDescription(choice.getDescription());

        return entity;
    }
}
