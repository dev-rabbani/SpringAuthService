package com.example.spring_auth_service.controller;

import com.example.spring_auth_service.model.dto.request.LoginRequest;
import com.example.spring_auth_service.model.dto.request.UserRegistrationRequest;
import com.example.spring_auth_service.model.dto.response.ApiResponse;
import com.example.spring_auth_service.model.dto.response.LoginResponse;
import com.example.spring_auth_service.model.dto.response.RegisteredUserResponse;
import com.example.spring_auth_service.service.AuthService;
import com.example.spring_auth_service.service.RefreshTokenService;
import com.example.spring_auth_service.util.HttpUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
    private final RefreshTokenService refreshTokenService;

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
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request,
                                                            HttpServletResponse response) {
        LoginResponse loginResponse = authService.login(request);

        HttpUtil.setRefreshTokenCookie(response, refreshTokenService.create(request.username()));

        return ResponseEntity.ok()
                .body(ApiResponse.<LoginResponse>builder()
                        .message(LOGIN_SUCCESSFUL)
                        .data(loginResponse)
                        .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        authService.logout(authorizationHeader);

        return ResponseEntity.ok()
                .body(ApiResponse.<Void>builder()
                        .message(LOGOUT_SUCCESSFUL)
                        .build());
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshAccessToken(@CookieValue(REFRESH_TOKEN_COOKIE_NAME) String refreshToken) {
        LoginResponse loginResponse = authService.generateNewAccessToken(refreshToken);

        return ResponseEntity.ok()
                .body(ApiResponse.<LoginResponse>builder()
                        .message(ACCESS_TOKEN_REFRESHED)
                        .data(loginResponse)
                        .build());
    }
}
