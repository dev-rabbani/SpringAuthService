package com.example.spring_auth_service.advice;

import com.example.spring_auth_service.exception.EmailNotVerifiedException;
import com.example.spring_auth_service.exception.InvalidRefreshTokenException;
import com.example.spring_auth_service.exception.RefreshTokenMissingException;
import com.example.spring_auth_service.model.dto.response.ValidationErrorResponse;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.example.spring_auth_service.model.enums.ExceptionConstant.METHOD_ARGUMENT_NOT_VALID;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = {EntityExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleRuntimeException(EntityExistsException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ValidationErrorResponse> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError ->
                        ValidationErrorResponse.builder()
                                .field(fieldError.getField())
                                .message(fieldError.getDefaultMessage())
                                .build())
                .toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, METHOD_ARGUMENT_NOT_VALID.getMessage());
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(value = {RefreshTokenMissingException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetail handleRefreshTokenMissingException(RefreshTokenMissingException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(value = {InvalidRefreshTokenException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetail handleInvalidRefreshTokenException(InvalidRefreshTokenException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(value = {EmailNotVerifiedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetail handleEmailNotVerifiedException(EmailNotVerifiedException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }
}
