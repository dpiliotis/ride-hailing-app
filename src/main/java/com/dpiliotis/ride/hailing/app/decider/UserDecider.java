package com.dpiliotis.ride.hailing.app.decider;

import com.dpiliotis.ride.hailing.app.entity.Ride;
import com.dpiliotis.ride.hailing.app.entity.Survey;
import org.springframework.stereotype.Component;

@Component
public class UserDecider {

    public Survey findNextSurvey(Ride ride) {
        // twist the dice
        return new Survey();
    }
}
