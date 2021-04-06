package com.dpiliotis.ride.hailing.app.repository;

import com.dpiliotis.ride.hailing.app.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
