package com.friend.controller;

import com.friend.config.Result;
import com.friend.entity.User;
import com.friend.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class userController {
    private final userService userservice;

    @Autowired
    public userController(userService userservice) {
        this.userservice = userservice;
    }

    @RequestMapping("/login")
    public Result<?> login(@RequestBody User user) {
        String account = user.getUserAccount();
        String password = user.getUserPassword();
        List<User> users = this.userservice.selectAllUsers();
        for (User user1 : users) {
            if (account.equals(user1.getUserAccount()) && password.equals(user1.getUserPassword())) {
                return Result.success(user);
            }
        }
        return Result.error("1", "Illegal User !");
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        List<User> users = this.userservice.selectAllUsers();
        for (User user1 : users) {
            if (user.getUserAccount().equals(user1.getUserAccount())) {
                return Result.error("1", "User already registered");
            }
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
    public Result<?>
    deleteUserByAccount(@PathVariable String userAccount) {
        this.userservice.deleteUserByAccount(userAccount);
        return Result.success();
    }
}
