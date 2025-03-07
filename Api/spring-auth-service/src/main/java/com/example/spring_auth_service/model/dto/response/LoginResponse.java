package com.example.spring_auth_service.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LoginResponse(@JsonProperty("access_token")
                            String accessToken,
                            @JsonProperty("expires_at")
                            LocalDateTime expiresAt) {
}
