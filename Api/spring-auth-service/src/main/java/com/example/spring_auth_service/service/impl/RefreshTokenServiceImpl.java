package com.example.spring_auth_service.service.impl;

import com.example.spring_auth_service.config.JwtConfig;
import com.example.spring_auth_service.model.entity.RefreshToken;
import com.example.spring_auth_service.model.entity.User;
import com.example.spring_auth_service.repository.RefreshTokenRepository;
import com.example.spring_auth_service.service.RefreshTokenService;
import com.example.spring_auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;
    private final JwtConfig jwtConfig;

    @Override
    public RefreshToken create(String username) {
        User user = userService.findByUsername(username);

         RefreshToken refreshToken = RefreshToken.builder()
                 .token(UUID.randomUUID().toString())
                 .expiryDate(LocalDateTime.now().plusSeconds(jwtConfig.getRefreshTokenExpiration() / 1000))
                 .user(user)
                 .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }

    @Override
    public boolean isValid(RefreshToken refreshToken) {
        return refreshToken != null && refreshToken.getExpiryDate().isAfter(LocalDateTime.now());
    }
}
