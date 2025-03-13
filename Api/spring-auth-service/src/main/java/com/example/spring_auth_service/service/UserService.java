package com.example.spring_auth_service.service;

import com.example.spring_auth_service.model.entity.User;

import java.util.Optional;

public interface UserService {
    User create(User user);
    User update(User user);
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
