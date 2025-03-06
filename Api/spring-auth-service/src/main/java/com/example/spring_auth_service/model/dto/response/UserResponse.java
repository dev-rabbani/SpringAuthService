package com.example.spring_auth_service.model.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponse(String firstName,
                           String lastName,
                           String username,
                           String email,
                           String phoneNumber,
                           LocalDate dateOfBirth) {
}
