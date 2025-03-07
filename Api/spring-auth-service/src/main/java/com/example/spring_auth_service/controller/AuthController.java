package com.example.spring_auth_service.controller;

import com.example.spring_auth_service.model.dto.request.LoginRequest;
import com.example.spring_auth_service.model.dto.request.UserRegistrationRequest;
import com.example.spring_auth_service.model.dto.response.ApiResponse;
import com.example.spring_auth_service.model.dto.response.LoginResponse;
import com.example.spring_auth_service.model.dto.response.RegisteredUserResponse;
import com.example.spring_auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.spring_auth_service.constant.ApiEndpointConstant.AUTH_ENDPOINT;
import static com.example.spring_auth_service.constant.ApplicationConstant.*;

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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse loginResponse = authService.login(request);

        return ResponseEntity.ok()
                .body(ApiResponse.<LoginResponse>builder()
                        .message(LOGIN_SUCCESSFUL)
                        .data(loginResponse)
                        .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String authorizationHeader) {
        authService.logout(authorizationHeader);

        return ResponseEntity.ok()
                .body(ApiResponse.<Void>builder()
                        .message(LOGOUT_SUCCESSFUL)
                        .build());
    }
}
