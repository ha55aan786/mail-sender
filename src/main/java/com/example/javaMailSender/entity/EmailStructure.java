package com.example.javaMailSender.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailStructure {
    private String to;
    private String CC;
    private String subject;
    private String body;
}
