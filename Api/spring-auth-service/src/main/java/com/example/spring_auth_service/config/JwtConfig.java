package com.example.spring_auth_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.jwt.config")
@Getter
@Setter
public class JwtConfig {
    private String encryptionKey;
    private long tokenExpiration;
    private long refreshTokenExpiration;
}
