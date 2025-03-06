package com.example.spring_auth_service.controller;

import com.example.spring_auth_service.model.dto.request.UserRegistrationRequest;
import com.example.spring_auth_service.model.dto.response.UserResponse;
import com.example.spring_auth_service.model.entity.User;
import com.example.spring_auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.spring_auth_service.constant.ApiEndpointConstant.AUTH_ENDPOINT;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = AUTH_ENDPOINT)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        return ResponseEntity.ok()
                .body(authService.registerUser(request));
    }
}
