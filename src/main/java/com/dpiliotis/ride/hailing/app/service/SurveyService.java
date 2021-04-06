package com.dpiliotis.ride.hailing.app.service;

import com.dpiliotis.ride.hailing.app.entity.Survey;

import java.util.List;

public interface SurveyService {

    List<Survey> getAllSurveys();
    Survey getSurvey(Long surveyId);
    Survey saveSurvey(Survey survey);
    void deleteSurvey(Long surveyId);

}
