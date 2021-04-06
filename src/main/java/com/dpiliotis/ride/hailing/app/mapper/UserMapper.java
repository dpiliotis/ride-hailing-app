package com.dpiliotis.ride.hailing.app.mapper;

import com.dpiliotis.ride.hailing.app.dto.UserDto;
import com.dpiliotis.ride.hailing.app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto convertToDto(User user) {

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setName(user.getName());

        return dto;
    }

    public User convertToEntity(UserDto user) {

        User entity = new User();

        entity.setId(user.getId());
        entity.setName(user.getName());

        return entity;
    }


}
