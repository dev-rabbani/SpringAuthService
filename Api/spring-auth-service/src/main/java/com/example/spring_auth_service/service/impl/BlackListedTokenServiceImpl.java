package com.example.spring_auth_service.service.impl;

import com.example.spring_auth_service.model.enums.RedisKey;
import com.example.spring_auth_service.service.BlackListedTokenService;
import com.example.spring_auth_service.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlackListedTokenServiceImpl implements BlackListedTokenService {
    private final CacheService cacheService;

    @Override
    public void markAsBlacklisted(String token, LocalDateTime expiresAt) {
        String redisKey = getRedisKey(token);
        long expiryDurationInMS = ChronoUnit.MILLIS.between(LocalDateTime.now(), expiresAt);

        cacheService.putWithExpiry(redisKey, token, expiryDurationInMS, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isBlacklisted(String token) {
        String redisKey = getRedisKey(token);

        return cacheService.get(redisKey, String.class)
                .map(StringUtils::isNotBlank)
                .orElse(false);
    }

    private String getRedisKey(String token) {
        return RedisKey.BLACKLISTED_TOKEN.format(token);
    }
}
