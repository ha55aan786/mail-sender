package com.example.javaMailSender.controller;

import com.example.javaMailSender.entity.EmailStructure;
import com.example.javaMailSender.service.MailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailSenderController {

    private final MailSenderService mailSenderService;

    public MailSenderController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailStructure emailStructure) {

        if (emailStructure.getCC().isEmpty() || emailStructure.getTo().isEmpty() || emailStructure.getBody().isEmpty() || emailStructure.getSubject().isEmpty()) {
            return ResponseEntity.status(400).body("invalid body");
        }
        mailSenderService.sendEmail(emailStructure);

        return ResponseEntity.ok("Email sent successfully");
    }
}
