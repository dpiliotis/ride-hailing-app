package com.dpiliotis.ride.hailing.app.repository;

import com.dpiliotis.ride.hailing.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
