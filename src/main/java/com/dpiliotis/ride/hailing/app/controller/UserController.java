package com.dpiliotis.ride.hailing.app.controller;

import com.dpiliotis.ride.hailing.app.dto.ApiResponse;
import com.dpiliotis.ride.hailing.app.dto.SurveyDto;
import com.dpiliotis.ride.hailing.app.dto.SurveyResponseDto;
import com.dpiliotis.ride.hailing.app.dto.UserDto;
import com.dpiliotis.ride.hailing.app.entity.SurveyResponse;
import com.dpiliotis.ride.hailing.app.entity.User;
import com.dpiliotis.ride.hailing.app.exception.ResourceNotFound;
import com.dpiliotis.ride.hailing.app.mapper.SurveyMapper;
import com.dpiliotis.ride.hailing.app.mapper.SurveyResponseMapper;
import com.dpiliotis.ride.hailing.app.mapper.UserMapper;
import com.dpiliotis.ride.hailing.app.service.SurveyResponseService;
import com.dpiliotis.ride.hailing.app.service.SurveyService;
import com.dpiliotis.ride.hailing.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/user")
public class UserController {

    private final UserService userService;
    private final SurveyService surveyService;
    private final SurveyResponseService surveyResponseService;
    private final UserMapper userMapper;
    private final SurveyMapper surveyMapper;
    private final SurveyResponseMapper surveyResponseMapper;

    @Autowired
    public UserController(UserService userService,
                          SurveyService surveyService,
                          SurveyResponseService surveyResponseService,
                          UserMapper userMapper,
                          SurveyMapper surveyMapper,
                          SurveyResponseMapper surveyResponseMapper) {
        this.userService = userService;
        this.surveyService = surveyService;
        this.surveyResponseService = surveyResponseService;
        this.userMapper = userMapper;
        this.surveyMapper = surveyMapper;
        this.surveyResponseMapper = surveyResponseMapper;
    }

    @GetMapping
    public ApiResponse<List<UserDto>> getUsers() {

        List<UserDto> users = userService.getAllUsers().stream().map(userMapper::convertToDto).collect(Collectors.toList());

        return new ApiResponse<>(users);
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserDto> getUser(@PathVariable Long userId) {
        UserDto user = userMapper.convertToDto(userService.getUser(userId));
        return new ApiResponse<>(user);
    }

    @PostMapping
    public ApiResponse<UserDto> insertUser(@RequestBody UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        User saved = userService.saveUser(user);
        return new ApiResponse<>(userMapper.convertToDto(saved));
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        user.setId(userId);
        User saved = userService.saveUser(user);
        return new ApiResponse<>(userMapper.convertToDto(saved));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}/survey")
    public ApiResponse<List<SurveyDto>> getUserSurveys(@PathVariable Long userId) {

        List<SurveyDto> surveys = surveyResponseService.getUserSurveyResponses(userId)
                .stream()
                .map(SurveyResponse::getSurvey)
                .map(surveyMapper::convertToDto)
                .collect(Collectors.toList());

        return new ApiResponse<>(surveys);
    }

    @GetMapping("/{userId}/survey/{surveyId}")
    public ApiResponse<SurveyDto> getUserSurvey(@PathVariable Long userId, @PathVariable Long surveyId) {

        SurveyResponse surveyResponse = surveyResponseService.getSurveyResponse(userId, surveyId)
                .stream()
                .findFirst()
                .orElseThrow(ResourceNotFound::new);
        SurveyDto survey = surveyMapper.convertToDto(surveyResponse.getSurvey());

        return new ApiResponse<>(survey);
    }

    @GetMapping("/{userId}/survey/{surveyId}/response")
    public ApiResponse<List<SurveyResponseDto>> getUserSurveyResponse(@PathVariable Long userId, @PathVariable Long surveyId) {

        List<SurveyResponseDto> responses = surveyResponseService.getSurveyResponse(userId, surveyId)
                .stream()
                .map(surveyResponseMapper::convertToDto)
                .collect(Collectors.toList());

        return new ApiResponse<>(responses);
    }

    @PostMapping("/{userId}/survey/{surveyId}/response")
    public ApiResponse<SurveyResponseDto> insertUserSurveyResponse(@PathVariable Long userId,
                                                                   @PathVariable Long surveyId,
                                                                   @RequestBody SurveyResponseDto surveyResponseDto) {

        SurveyResponse response = surveyResponseMapper.convertToEntity(surveyResponseDto);
        response.setUser(userService.getUser(userId));
        response.setSurvey(surveyService.getSurvey(surveyId));
        SurveyResponse saved = surveyResponseService.saveSurveyResponse(response);

        return new ApiResponse<>(surveyResponseMapper.convertToDto(saved));
    }

    @PutMapping("/{userId}/survey/{surveyId}/response/{surveyResponseId}")
    public ApiResponse<SurveyResponseDto> updateUserSurveyResponse(@PathVariable Long userId,
                                                                   @PathVariable Long surveyId,
                                                                   @PathVariable Long surveyResponseId,
                                                                   @RequestBody SurveyResponseDto surveyResponseDto) {

        SurveyResponse response = surveyResponseMapper.convertToEntity(surveyResponseDto);
        response.setId(surveyResponseId);
        response.setUser(userService.getUser(userId));
        response.setSurvey(surveyService.getSurvey(surveyId));
        SurveyResponse saved = surveyResponseService.saveSurveyResponse(response);

        return new ApiResponse<>(surveyResponseMapper.convertToDto(saved));
    }

    @DeleteMapping("/{userId}/survey/{surveyId}/response/{surveyResponseId}")
    public void deleteUserSurveyResponse(@PathVariable Long userId,
                                         @PathVariable Long surveyId,
                                         @PathVariable Long surveyResponseId) {

        surveyResponseService.deleteSurveyResponse(surveyResponseId);
    }
}
