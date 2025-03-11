package com.example.spring_auth_service.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendMailAsync(String toAddress, String subject, String body) throws MessagingException;
}
