package com.dpiliotis.ride.hailing.app.controller;

import com.dpiliotis.ride.hailing.app.dto.ApiResponse;
import com.dpiliotis.ride.hailing.app.dto.SurveyDto;
import com.dpiliotis.ride.hailing.app.entity.Survey;
import com.dpiliotis.ride.hailing.app.mapper.SurveyMapper;
import com.dpiliotis.ride.hailing.app.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/survey")
public class SurveyController {

    private final SurveyService surveyService;
    private final SurveyMapper surveyMapper;

    @Autowired
    public SurveyController(SurveyService surveyService, SurveyMapper surveyMapper) {
        this.surveyService = surveyService;
        this.surveyMapper = surveyMapper;
    }

    @GetMapping
    public ApiResponse<List<SurveyDto>> getAllSurveys() {

        List<SurveyDto> surveys = surveyService.getAllSurveys()
                .stream()
                .map(surveyMapper::convertToDto)
                .collect(Collectors.toList());

        return new ApiResponse<>(surveys);
    }

    @GetMapping("/{surveyId}")
    public ApiResponse<SurveyDto> getSurvey(@PathVariable Long surveyId) {

        SurveyDto survey = surveyMapper.convertToDto(surveyService.getSurvey(surveyId));

        return new ApiResponse<>(survey);
    }

    @PostMapping
    public ApiResponse<SurveyDto> insertSurvey(@RequestBody SurveyDto surveyDto) {

        Survey survey = surveyService.saveSurvey(surveyMapper.convertToEntity(surveyDto));
        SurveyDto saved = surveyMapper.convertToDto(survey);

        return new ApiResponse<>(saved);
    }

    @PutMapping("/{surveyId}")
    public ApiResponse<SurveyDto> updateSurvey(@PathVariable Long surveyId, @RequestBody SurveyDto surveyDto) {

        Survey survey = surveyMapper.convertToEntity(surveyDto);
        survey.setId(surveyId);
        Survey saved = surveyService.saveSurvey(survey);
        SurveyDto result = surveyMapper.convertToDto(saved);

        return new ApiResponse<>(result);
    }

    @DeleteMapping("/{surveyId}")
    public void saveSurvey(@PathVariable Long surveyId) {
        surveyService.deleteSurvey(surveyId);
    }
}
