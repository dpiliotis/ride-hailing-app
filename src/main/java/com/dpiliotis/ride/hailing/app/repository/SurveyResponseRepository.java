package com.dpiliotis.ride.hailing.app.repository;

import com.dpiliotis.ride.hailing.app.entity.Ride;
import com.dpiliotis.ride.hailing.app.entity.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {

    List<SurveyResponse> findSurveyResponseByUserId(Long userId);
    List<SurveyResponse> findSurveyResponseByUserIdAndSurveyId(Long userId, Long surveyId);
}
