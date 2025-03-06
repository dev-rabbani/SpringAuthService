package com.example.spring_auth_service.model.dto.response;

import lombok.Builder;

@Builder
public record ValidationErrorResponse(String field, String message) {
}
