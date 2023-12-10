package com.friend.service;

import com.friend.entity.User;
import com.friend.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    private final userMapper usermapper;

    @Autowired
    public userService(userMapper usermapper) {
        this.usermapper = usermapper;
    }

    public List<User> selectAllUsers() {
        return usermapper.selectAllUsers();
    }

    public User selectByAccount(String userAccount) {
        return usermapper.selectByAccount(userAccount);
    }

    public User selectByMail(String useremail) {
        return usermapper.selectByMail(useremail);
    }

    public void addUser(User user) {
        usermapper.addUser(user);
    }

    public void updateUser(User user) {
        usermapper.updateUser(user);
    }

    public void deleteUserByAccount(String userAccount) {
        usermapper.deleteUserByAccount(userAccount);
    }

}
