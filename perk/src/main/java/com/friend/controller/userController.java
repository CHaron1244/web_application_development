package com.friend.controller;

import com.friend.config.Result;
import com.friend.entity.User;
import com.friend.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    private final userService userservice;

    @Autowired
    public userController(userService userservice) {
        this.userservice = userservice;
    }

    @RequestMapping("/login")
    public Result<?> login(@RequestBody User user) {
        if (user == null || user.getUserAccount() == null || user.getUserPassword() == null) {
            return Result.error("1", "Illegal User !");
        }
        String account = user.getUserAccount();
        String password = user.getUserPassword();
        User dbUser = this.userservice.selectByAccount(account);
        if (dbUser != null && password != null && password.equals(dbUser.getUserPassword())) {
            return Result.success(dbUser);
        }
        return Result.error("1", "Illegal User !");
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        if (user == null || user.getUserAccount() == null) {
            return Result.error("1", "Illegal User !");
        }
        User dbUser = this.userservice.selectByAccount(user.getUserAccount());
        if (dbUser != null) {
            return Result.error("1", "User already registered");
        }
        userservice.addUser(user);
        return Result.success();
    }

    @RequestMapping("/allUsers")
    public Result<?> selectAllUsers() {
        return Result.success(this.userservice.selectAllUsers());
    }

    @RequestMapping("/selectByAccount/{userAccount}") // userAccount
    public Result<?> selectByAccount(@PathVariable("userAccount") String userAccount) {
        return Result.success(this.userservice.selectByAccount(userAccount));
    }

    @RequestMapping("/selectByMail") // userAccount
    public Result<?> selectByMail(@RequestBody String useremail) {
        return Result.success(this.userservice.selectByMail(useremail));
    }


    @PostMapping("/addUser")
    public Result<?> addUser(@RequestBody User user) {
        this.userservice.addUser(user);
        return Result.success();
    }

    @RequestMapping("/updateUser")
    public Result<?> updateUser(@RequestBody User user) {
        this.userservice.updateUser(user);
        return Result.success();
    }

    @RequestMapping("/deleteUserByAccount/{userAccount}")
    public Result<?> deleteUserByAccount(@PathVariable String userAccount) {
        this.userservice.deleteUserByAccount(userAccount);
        return Result.success();
    }
}
