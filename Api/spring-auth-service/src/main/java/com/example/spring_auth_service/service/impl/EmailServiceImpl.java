package com.example.spring_auth_service.service.impl;

import com.example.spring_auth_service.config.EmailConfig;
import com.example.spring_auth_service.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final EmailConfig emailConfig;


    @Async
    @Override
    public void sendMailAsync(String toAddress, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(emailConfig.getSender());
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);
    }
}
