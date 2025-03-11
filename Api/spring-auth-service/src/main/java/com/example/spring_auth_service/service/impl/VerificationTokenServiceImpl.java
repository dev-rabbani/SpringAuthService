package com.example.spring_auth_service.service.impl;

import com.example.spring_auth_service.config.VerificationTokenConfig;
import com.example.spring_auth_service.model.entity.User;
import com.example.spring_auth_service.model.entity.VerificationToken;
import com.example.spring_auth_service.repository.VerificationTokenRepository;
import com.example.spring_auth_service.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenConfig verificationTokenConfig;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken create(User user) {
        Optional<VerificationToken> oldTokenOptional = verificationTokenRepository.findByUser(user);
        oldTokenOptional.ifPresent(verificationTokenRepository::delete);

        VerificationToken token = VerificationToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryTime(LocalDateTime.now()
                        .plus(Duration.ofMillis(verificationTokenConfig.getExpiration())))
                .user(user)
                .build();

        return verificationTokenRepository.save(token);
    }
}
