package com.dpiliotis.ride.hailing.app.service;

import com.dpiliotis.ride.hailing.app.entity.Ride;
import com.dpiliotis.ride.hailing.app.exception.ResourceNotFound;
import com.dpiliotis.ride.hailing.app.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaRideService implements RideService {

    private final RideRepository rideRepository;

    @Autowired
    public JpaRideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public List<Ride> getUserRides(Long userId) {
        return rideRepository.findRidesByUserId(userId);
    }

    public Ride getRide(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(() -> new ResourceNotFound("Ride not found"));
    }

    public Ride saveRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public void deleteRide(Long rideId) {
        rideRepository.deleteById(rideId);
    }
}
