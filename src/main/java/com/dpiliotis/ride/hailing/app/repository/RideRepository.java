package com.dpiliotis.ride.hailing.app.repository;

import com.dpiliotis.ride.hailing.app.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findRidesByUserId(Long userId);
}
