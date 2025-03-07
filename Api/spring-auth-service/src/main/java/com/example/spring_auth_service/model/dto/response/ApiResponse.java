package com.example.spring_auth_service.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record ApiResponse<T>(String message,
                             @JsonInclude(JsonInclude.Include.NON_NULL)
                             T data) {
}
