package com.example.spring_auth_service.util;

import com.example.spring_auth_service.model.entity.RefreshToken;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.example.spring_auth_service.constant.ApiEndpointConstant.REFRESH_TOKEN_ENDPOINT;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtil {
    private static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";

    public static void setRefreshTokenCookie(HttpServletResponse response, RefreshToken refreshToken) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_COOKIE_NAME, refreshToken.getToken());
        cookie.setPath(REFRESH_TOKEN_ENDPOINT);
        cookie.setHttpOnly(true);

        long maxAgeInSeconds = Duration.between(LocalDateTime.now(), refreshToken.getExpiryDate()).getSeconds();
        cookie.setMaxAge((int) maxAgeInSeconds);

        response.addCookie(cookie);
    }
}
