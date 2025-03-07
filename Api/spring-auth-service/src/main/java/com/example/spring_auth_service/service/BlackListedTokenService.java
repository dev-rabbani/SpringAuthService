package com.example.spring_auth_service.service;

import java.time.LocalDateTime;

public interface BlackListedTokenService {
    void markAsBlacklisted(String token, LocalDateTime expiresAt);
    boolean isBlacklisted(String token);
}
