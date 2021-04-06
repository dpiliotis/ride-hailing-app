package com.dpiliotis.ride.hailing.app.controller;

import com.dpiliotis.ride.hailing.app.decider.UserDecider;
import com.dpiliotis.ride.hailing.app.dto.ActionDto;
import com.dpiliotis.ride.hailing.app.dto.ApiResponse;
import com.dpiliotis.ride.hailing.app.dto.RideDto;
import com.dpiliotis.ride.hailing.app.entity.Ride;
import com.dpiliotis.ride.hailing.app.entity.Survey;
import com.dpiliotis.ride.hailing.app.entity.User;
import com.dpiliotis.ride.hailing.app.mapper.RideMapper;
import com.dpiliotis.ride.hailing.app.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/ride")
public class RideController {

    private final RideService rideService;
    private final RideMapper rideMapper;
    private final UserDecider userDecider;

    @Autowired
    public RideController(RideService rideService, RideMapper rideMapper, UserDecider userDecider) {
        this.rideService = rideService;
        this.rideMapper = rideMapper;
        this.userDecider = userDecider;
    }

    @GetMapping
    public ApiResponse<List<RideDto>> getAllRides() {

        List<RideDto> rides = rideService.getAllRides()
                .stream()
                .map(rideMapper::convertToDto)
                .collect(Collectors.toList());

        return new ApiResponse<>(rides);
    }

    @GetMapping("/{rideId}")
    public ApiResponse<RideDto> getRide(@PathVariable Long rideId) {
        RideDto ride = rideMapper.convertToDto(rideService.getRide(rideId));
        return new ApiResponse<>(ride);
    }

    @PostMapping
    public ApiResponse<RideDto> insertRide(@RequestBody RideDto rideDto) {

        Ride saved = rideService.saveRide(rideMapper.convertToEntity(rideDto));
        RideDto ride = rideMapper.convertToDto(saved);

        ApiResponse<RideDto> response = new ApiResponse<>(ride);

        Survey nextSurvey = userDecider.findNextSurvey(saved);
        ActionDto actionDto = buildSurveyAction(nextSurvey, saved.getUser());

        response.setActions(Collections.singletonList(actionDto));

        return response;
    }

    @PutMapping("/{rideId}")
    public ApiResponse<RideDto> updateRide(@PathVariable Long rideId, @RequestBody RideDto rideDto) {

        Ride ride = rideMapper.convertToEntity(rideDto);
        ride.setId(rideId);
        Ride saved = rideService.saveRide(ride);
        RideDto result = rideMapper.convertToDto(saved);

        return new ApiResponse<>(result);
    }

    @DeleteMapping("/{rideId}")
    public void saveRide(@PathVariable Long rideId) {
        rideService.deleteRide(rideId);
    }

    private ActionDto buildSurveyAction(Survey survey, User user) {
        ActionDto action = new ActionDto();
        action.setName("next Survey");
        action.setLink("next Survey link");
        return action;
    }
}
