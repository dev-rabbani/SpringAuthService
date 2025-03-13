package com.example.spring_auth_service.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ForgotPasswordRequest(@NotBlank(message = "Email can not be blank")
                                    @Email(message = "Please provide a valid email address")
                                    @Size(max = 50, message = "Email should not be more that 50 characters long")
                                    String email) {
}
