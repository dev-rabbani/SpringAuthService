package com.example.spring_auth_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(@NotBlank(message = "Token can not be blank")
                                   String token,
                                   @NotBlank(message = "Password can not be blank")
                                   String password) {
}
