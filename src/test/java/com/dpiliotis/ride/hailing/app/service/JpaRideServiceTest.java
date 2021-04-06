package com.dpiliotis.ride.hailing.app.service;

import com.dpiliotis.ride.hailing.app.entity.Ride;
import com.dpiliotis.ride.hailing.app.exception.ResourceNotFound;
import com.dpiliotis.ride.hailing.app.repository.RideRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JpaRideServiceTest {

    private RideRepository rideRepository;
    private JpaRideService service;

    @BeforeEach
    public void setup() {
        rideRepository = mock(RideRepository.class);
        service = new JpaRideService(rideRepository);
    }

    @Test
    public void testGetAllRides() {

        // given
        List<Ride> rides = Collections.singletonList(new Ride());
        when(rideRepository.findAll()).thenReturn(rides);

        // when
        List<Ride> result = service.getAllRides();

        // then
        Assertions.assertEquals(rides, result);
    }

    @Test
    public void testGetUserRides() {

        // given
        Long userId = 1L;
        List<Ride> rides = Collections.singletonList(new Ride());
        when(rideRepository.findRidesByUserId(userId)).thenReturn(rides);

        // when
        List<Ride> result = service.getUserRides(userId);

        // then
        Assertions.assertEquals(rides, result);
    }

    @Test
    public void testGetRide() {

        // given
        Long rideId = 1L;
        Ride ride = new Ride();
        when(rideRepository.findById(rideId)).thenReturn(Optional.of(ride));

        // when
        Ride result = service.getRide(rideId);

        // then
        Assertions.assertEquals(ride, result);
    }

    @Test
    public void testGetRideNotExists() {

        // given
        Long rideId = 1L;
        when(rideRepository.findById(rideId)).thenReturn(Optional.empty());

        // when
        ResourceNotFound e = Assertions.assertThrows(ResourceNotFound.class, () -> service.getRide(rideId));

        // then
        assertEquals("Ride not found", e.getMessage());
    }

    @Test
    public void testSaveRide() {

        // given
        Ride ride = new Ride();
        when(rideRepository.save(ride)).thenReturn(ride);

        // when
        Ride result = service.saveRide(ride);

        // then
        assertEquals(ride, result);
    }

    @Test
    public void testDeleteRide() {

        // given
        Long rideId = 1L;

        // when
        service.deleteRide(rideId);

        // then
        verify(rideRepository, times(1)).deleteById(rideId);
    }

}
