package com.example.spring_auth_service.service.impl;

import com.example.spring_auth_service.config.VerificationTokenConfig;
import com.example.spring_auth_service.model.entity.User;
import com.example.spring_auth_service.model.entity.VerificationToken;
import com.example.spring_auth_service.repository.VerificationTokenRepository;
import com.example.spring_auth_service.service.VerificationTokenService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.example.spring_auth_service.model.enums.ExceptionConstant.VERIFICATION_TOKEN_NOT_FOUND;

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

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException(VERIFICATION_TOKEN_NOT_FOUND.getMessage()));
    }

    @Override
    public void delete(VerificationToken token) {
        verificationTokenRepository.delete(token);
    }
}
