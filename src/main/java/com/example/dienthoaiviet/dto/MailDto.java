package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    private String from;
    private String to;
    private String subject;
    private String body;
    private int code;
}
