package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import org.springframework.stereotype.Service;



@Service
public class MailService {
    @Autowired
    private JavaMailSender sender;
    public void  sendMail(MailDto mail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("duyyeuanh3062015@gmail.com");
        message.setText(mail.getBody());
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        sender.send(message);
    }
}
