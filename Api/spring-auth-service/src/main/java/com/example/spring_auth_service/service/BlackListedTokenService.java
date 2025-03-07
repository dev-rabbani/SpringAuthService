package com.example.spring_auth_service.service;

import java.util.Date;

public interface BlackListedTokenService {
    void markAsBlacklisted(String token, Date expirationDate);
    boolean isBlacklisted(String token);
}
