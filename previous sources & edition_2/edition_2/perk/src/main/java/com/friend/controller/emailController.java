package com.friend.controller;

import com.friend.config.Result;
import com.friend.entity.BuyLog;
import com.friend.entity.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/email")
public class emailController {

    @Autowired
    public userController usercontroller;
    @Autowired
    public JavaMailSender jms;

    @Value("${spring.mail.from}")
    private String from;

    public emailController(userController usercontroller,JavaMailSender jms) {
        this.usercontroller = usercontroller;
        this.jms = jms;
    }

    @PostMapping("/send")
    public Result<?> send(@RequestBody BuyLog buyLog) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);

        Result<?> res = usercontroller.selectByAccount(buyLog.getUserAccount());
        User user = (User) res.getData();
        message.setTo(user.getUserEmail());

        message.setSubject("A NEWSLETTER FROM GUNTHER's CENTRAL PERK");

        message.setText("Dear " + buyLog.getUserAccount() + ",\n\n" +
                "Thank you for using the Gunther's central perk shopping platform. Your purchased item \"" +
                buyLog.getGoodName() + "\" has been shipped. Please check and confirm upon receiving.Thanks for your coming!"
                + "\n\nSEND TIME: " + now.format(formatter));
        jms.send(message);
        return Result.success();
    }
}
