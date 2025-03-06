package com.example.spring_auth_service.controller;

import com.example.spring_auth_service.model.dto.request.UserRegistrationRequest;
import com.example.spring_auth_service.model.dto.response.ApiResponse;
import com.example.spring_auth_service.model.dto.response.RegisteredUserResponse;
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
import static com.example.spring_auth_service.constant.ApplicationConstant.USER_REGISTRATION_SUCCESSFUL;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = AUTH_ENDPOINT)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisteredUserResponse>> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        RegisteredUserResponse user = authService.registerUser(request);

        return ResponseEntity.ok()
                .body(ApiResponse.<RegisteredUserResponse>builder()
                        .message(USER_REGISTRATION_SUCCESSFUL)
                        .data(user)
                        .build());
    }
}
