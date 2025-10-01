package com.example.javaMailSender.service;

import com.example.javaMailSender.entity.EmailStructure;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private final JavaMailSender javaMailSender;
    private final JwtValidatorService jwtValidatorService;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public MailSenderService(JavaMailSender javaMailSender, JwtValidatorService jwtValidatorService) {
        this.javaMailSender = javaMailSender;
        this.jwtValidatorService = jwtValidatorService;
    }

    public void sendEmail(EmailStructure emailStructure, String jwtToken) {
        try {
            jwtValidatorService.validateToken(jwtToken);
        } catch (Exception e) {
            throw new RuntimeException("Token validation failed: " + e.getMessage(), e);
        }
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(emailStructure.getTo());
            helper.setCc(emailStructure.getCC());
            helper.setSubject(emailStructure.getSubject());
            helper.setText(emailStructure.getBody(), true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }
}
