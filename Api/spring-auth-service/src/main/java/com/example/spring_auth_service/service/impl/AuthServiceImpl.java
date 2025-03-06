package com.example.spring_auth_service.service.impl;

import com.example.spring_auth_service.model.dto.request.UserRegistrationRequest;
import com.example.spring_auth_service.model.dto.response.RegisteredUserResponse;
import com.example.spring_auth_service.model.entity.User;
import com.example.spring_auth_service.service.AuthService;
import com.example.spring_auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisteredUserResponse registerUser(UserRegistrationRequest request) {
        return mapToUserResponse(userService.save(mapToUser(request)));
    }

    private User mapToUser(UserRegistrationRequest request) {
        return User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .dateOfBirth(request.dateOfBirth())
                .build();
    }

    private RegisteredUserResponse mapToUserResponse(User user) {
        return RegisteredUserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
