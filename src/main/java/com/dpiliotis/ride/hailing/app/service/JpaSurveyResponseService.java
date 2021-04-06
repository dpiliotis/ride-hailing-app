package com.dpiliotis.ride.hailing.app.service;

import com.dpiliotis.ride.hailing.app.entity.SurveyResponse;
import com.dpiliotis.ride.hailing.app.exception.ResourceNotFound;
import com.dpiliotis.ride.hailing.app.repository.SurveyResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaSurveyResponseService implements SurveyResponseService {

    private final SurveyResponseRepository surveyResponseRepository;

    @Autowired
    public JpaSurveyResponseService(SurveyResponseRepository surveyResponseRepository) {
        this.surveyResponseRepository = surveyResponseRepository;
    }

    public List<SurveyResponse> getUserSurveyResponses(Long userId) {
        return surveyResponseRepository.findSurveyResponseByUserId(userId);
    }

    public SurveyResponse getSurveyResponse(Long surveyResponseId) {
        return surveyResponseRepository.findById(surveyResponseId)
                .orElseThrow(() -> new ResourceNotFound("Survey not found"));
    }

    @Override
    public List<SurveyResponse> getSurveyResponse(Long userId, Long surveyId) {
        return surveyResponseRepository.findSurveyResponseByUserIdAndSurveyId(userId, surveyId);
    }

    public SurveyResponse saveSurveyResponse(SurveyResponse surveyResponse) {
        return surveyResponseRepository.save(surveyResponse);
    }

    public void deleteSurveyResponse(Long surveyResponseId) {
        surveyResponseRepository.deleteById(surveyResponseId);
    }
}
