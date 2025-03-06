package com.example.spring_auth_service.model.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RegisteredUserResponse(String username,
                                     String email) {
}
