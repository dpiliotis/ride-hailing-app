package com.dpiliotis.ride.hailing.app.mapper;

import com.dpiliotis.ride.hailing.app.dto.RideDto;
import com.dpiliotis.ride.hailing.app.entity.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RideMapper {

    private final UserMapper userMapper;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public RideMapper(UserMapper userMapper, VehicleMapper vehicleMapper) {
        this.userMapper = userMapper;
        this.vehicleMapper = vehicleMapper;
    }

    public RideDto convertToDto(Ride ride) {

        RideDto dto = new RideDto();

        dto.setId(ride.getId());
        dto.setUser(userMapper.convertToDto(ride.getUser()));
        dto.setVehicle(vehicleMapper.convertToDto(ride.getVehicle()));
        dto.setFromPoint(ride.getFromPoint());
        dto.setToPoint(ride.getToPoint());
        dto.setRating(ride.getRating());

        return dto;
    }

    public Ride convertToEntity(RideDto ride) {

        Ride entity = new Ride();

        entity.setId(ride.getId());
        entity.setUser(userMapper.convertToEntity(ride.getUser()));
        entity.setVehicle(vehicleMapper.convertToEntity(ride.getVehicle()));
        entity.setFromPoint(ride.getFromPoint());
        entity.setToPoint(ride.getToPoint());
        entity.setRating(ride.getRating());

        return entity;
    }
}
