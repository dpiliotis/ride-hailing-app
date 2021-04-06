package com.dpiliotis.ride.hailing.app.mapper;

import com.dpiliotis.ride.hailing.app.dto.VehicleDto;
import com.dpiliotis.ride.hailing.app.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleDto convertToDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();

        dto.setId(vehicle.getId());
        dto.setDescription(vehicle.getDescription());

        return dto;
    }

    public Vehicle convertToEntity(VehicleDto vehicle) {
        Vehicle entity = new Vehicle();

        entity.setId(vehicle.getId());
        entity.setDescription(vehicle.getDescription());

        return entity;
    }
}
