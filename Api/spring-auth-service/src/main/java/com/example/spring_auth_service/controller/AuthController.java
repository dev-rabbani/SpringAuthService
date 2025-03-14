package com.example.spring_auth_service.controller;

import com.example.spring_auth_service.model.dto.request.ForgotPasswordRequest;
import com.example.spring_auth_service.model.dto.request.LoginRequest;
import com.example.spring_auth_service.model.dto.request.ResetPasswordRequest;
import com.example.spring_auth_service.model.dto.request.UserRegistrationRequest;
import com.example.spring_auth_service.model.dto.response.ApiResponse;
import com.example.spring_auth_service.model.dto.response.LoginResponse;
import com.example.spring_auth_service.model.dto.response.RegisteredUserResponse;
import com.example.spring_auth_service.service.AuthService;
import com.example.spring_auth_service.util.HttpUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Auth Controller")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisteredUserResponse>> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        RegisteredUserResponse user = authService.registerUser(request);

        return ResponseEntity.ok(ApiResponse.success(USER_REGISTRATION_SUCCESSFUL, user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request,
                                                            HttpServletResponse response) {
        LoginResponse loginResponse = authService.login(request);
        HttpUtil.setRefreshTokenCookie(response, loginResponse.refreshToken());

        return ResponseEntity.ok(ApiResponse.success(LOGIN_SUCCESSFUL, loginResponse));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                                    HttpServletResponse response) {
        authService.logout(authorizationHeader);
        HttpUtil.deleteRefreshTokenCookie(response);

        return ResponseEntity.ok(ApiResponse.success(LOGOUT_SUCCESSFUL));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshAccessToken(@CookieValue(REFRESH_TOKEN_COOKIE_NAME) String refreshToken) {
        LoginResponse loginResponse = authService.generateNewAccessToken(refreshToken);

        return ResponseEntity.ok(ApiResponse.success(ACCESS_TOKEN_REFRESHED, loginResponse));
    }

    @GetMapping("/verify")
    public ResponseEntity<ApiResponse<Void>> verifyUser(@RequestParam(name = "token") String token) {
        authService.verifyUser(token);

        return ResponseEntity.ok(ApiResponse.success(EMAIL_VERIFICATION_SUCCESSFUL));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<Void>> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        authService.sendPasswordResetEmail(request.email());

        return ResponseEntity.ok(ApiResponse.success(PASSWORD_RESET_INSTRUCTIONS_SENT));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<Void>> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);

        return ResponseEntity.ok(ApiResponse.success(PASSWORD_RESET_SUCCESSFUL));
    }
}
