package com.example.spring_auth_service.service;

import com.example.spring_auth_service.model.entity.User;
import com.example.spring_auth_service.model.entity.VerificationToken;

public interface VerificationTokenService {
    VerificationToken create(User user);
    VerificationToken findByToken(String token);
    void delete(VerificationToken token);
}
