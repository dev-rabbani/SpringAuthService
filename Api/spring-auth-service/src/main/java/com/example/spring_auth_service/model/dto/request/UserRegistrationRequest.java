package com.example.spring_auth_service.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserRegistrationRequest(@NotBlank(message = "First name can not be blank")
                                      @Size(min = 2, max = 25, message = "First name should be between 2 and 25 characters long.")
                                      String firstName,
                                      @NotBlank(message = "Last name can not be blank")
                                      @Size(min = 2, max = 25, message = "Last name should be between 2 and 25 characters long.")
                                      String lastName,
                                      @NotBlank(message = "Username can not be blank")
                                      @Size(min = 3, max = 10, message = "Username should be between 3 and 10 characters long")
                                      String username,
                                      @NotBlank(message = "Password can not be blank")
                                      String password,
                                      @NotBlank(message = "Email can not be blank")
                                      @Email(message = "Please provide a valid email address")
                                      @Size(max = 50, message = "Email should not be more that 50 characters long")
                                      String email,
                                      @Size(min = 10, max = 15, message = "Phone number should be between 10 and 15 characters long")
                                      String phoneNumber,
                                      @Past(message = "Date of birth must be before today.")
                                      LocalDate dateOfBirth) {
}
