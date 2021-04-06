package com.dpiliotis.ride.hailing.app.service;

import com.dpiliotis.ride.hailing.app.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUser(Long userId);
    User saveUser(User user);
    void deleteUser(Long userId);

}
