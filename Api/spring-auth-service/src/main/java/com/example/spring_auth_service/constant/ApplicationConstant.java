package com.example.spring_auth_service.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConstant {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final String USER_REGISTRATION_SUCCESSFUL = "User registered successfully.";
    public static final String LOGIN_SUCCESSFUL = "Login successfully.";
    public static final String LOGOUT_SUCCESSFUL = "Logout successfully.";
    public static final String ACCESS_TOKEN_REFRESHED = "Access token refreshed successfully.";
    public static final String EMAIL_VERIFICATION_SUCCESSFUL = "Email verified successfully.";
}
