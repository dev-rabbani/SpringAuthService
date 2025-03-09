package com.example.spring_auth_service.service;

import com.example.spring_auth_service.model.entity.User;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
}
