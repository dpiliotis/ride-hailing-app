package com.dpiliotis.ride.hailing.app.service;

import com.dpiliotis.ride.hailing.app.entity.Survey;
import com.dpiliotis.ride.hailing.app.exception.ResourceNotFound;
import com.dpiliotis.ride.hailing.app.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaSurveyService implements SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public JpaSurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    public Survey getSurvey(Long surveyId) {
        return surveyRepository.findById(surveyId).orElseThrow(() -> new ResourceNotFound("Survey not found"));
    }

    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public void deleteSurvey(Long surveyId) {
        surveyRepository.deleteById(surveyId);
    }
}
