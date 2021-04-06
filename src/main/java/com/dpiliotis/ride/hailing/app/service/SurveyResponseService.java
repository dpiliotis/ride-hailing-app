package com.dpiliotis.ride.hailing.app.service;

import com.dpiliotis.ride.hailing.app.entity.SurveyResponse;

import java.util.List;

public interface SurveyResponseService {

    List<SurveyResponse> getUserSurveyResponses(Long userId);
    SurveyResponse getSurveyResponse(Long surveyResponseId);
    List<SurveyResponse> getSurveyResponse(Long userId, Long surveyId);
    SurveyResponse saveSurveyResponse(SurveyResponse surveyResponse);
    void deleteSurveyResponse(Long surveyResponseId);

}
