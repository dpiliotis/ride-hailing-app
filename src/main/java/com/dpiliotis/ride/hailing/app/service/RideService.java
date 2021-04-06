package com.dpiliotis.ride.hailing.app.service;

import com.dpiliotis.ride.hailing.app.entity.Ride;

import java.util.List;

public interface RideService {

    List<Ride> getAllRides();
    List<Ride> getUserRides(Long userId);
    Ride getRide(Long rideId);
    Ride saveRide(Ride ride);
    void deleteRide(Long rideId);

}
