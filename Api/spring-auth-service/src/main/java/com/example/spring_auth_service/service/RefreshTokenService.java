package com.example.spring_auth_service.service;

import com.example.spring_auth_service.model.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken create(String username);
    Optional<RefreshToken> findByToken(String refreshToken);
}
