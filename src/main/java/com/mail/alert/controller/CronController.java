package com.mail.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CronController {

    @Autowired
    EmailController emailController;

    @Scheduled(cron = "0 0 9 * * ?")
    public void cronJob() {
        emailController.sendEmail();
    }

}
