package com.example.spring_auth_service.service.impl;

import com.example.spring_auth_service.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @Override
    public RegisteredUserResponse registerUser(UserRegistrationRequest request) {
        User user = userMapper.userRegistrationRequestToUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User registeredUser = userService.save(user);

        return userMapper.userToRegisteredUserResponse(registeredUser);
    }
}
