package com.example.spring_auth_service.service;

import com.example.spring_auth_service.model.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken create(String username);
}
