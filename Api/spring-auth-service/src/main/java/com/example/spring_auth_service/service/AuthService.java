package com.example.spring_auth_service.service;

import com.example.spring_auth_service.model.dto.request.UserRegistrationRequest;
import com.example.spring_auth_service.model.dto.response.RegisteredUserResponse;

public interface AuthService {
    RegisteredUserResponse registerUser(UserRegistrationRequest request);
}
