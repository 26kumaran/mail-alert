package com.mail.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/sendEmail")
    void sendEmail() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("xxxx@gmail.com");
        String[] mailList = {"xxxx@gmail.com", "tcshd.kumaran@gmail.com"};
        message.setTo(mailList);
        message.setText("This is the test mail");
        message.setSubject("Mail Test");
        javaMailSender.send(message);

    }

    @RequestMapping(value = "/sendEmailAttachment")
    void sendEmailWithAttachment() throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("xxxx@gmail.com");
        helper.setSubject("Testing mail alert");
        helper.setText("<table style='width:70%;border: 1px solid black;border-collapse: collapse;'>" +
                "<tr style='background-color: lightblue;'>" +
                "<th style='border: 1px solid black;border-collapse: collapse;'>Code</th>" +
                "<th style='border: 1px solid black;border-collapse: collapse;'>Date</th>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid black;border-collapse: collapse;'>SOI_V</td>" +
                "<td style='border: 1px solid black;border-collapse: collapse;'>2022</td>" +
                "</tr>" +
                "</table>", true);
        /* Possibilities to point a file */
        //FileSystemResource file = new FileSystemResource(new File("src/main/resources/picture.jpeg"));
        //helper.addInline("id1", new ClassPathResource("images/pic.jpeg")); //which points to the resource folder
        //helper.addAttachment("picture.jpeg", file);
        javaMailSender.send(msg);

    }
}
