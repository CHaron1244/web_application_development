package com.friend.mapper;

import com.friend.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@Mapper
public interface userMapper {
    @Select("SELECT * FROM User")
    List<User> selectAllUsers();

    @Select("SELECT * FROM User WHERE user_account=#{userAccount}")
    User selectByAccount(String userAccount);

    @Select("SELECT * FROM User WHERE user_email=#{useremail}")
    User selectByMail(String useremail);

    @Insert("INSERT INTO User VALUES (#{user.userAccount}, #{user.userPassword}, #{user.userEmail}, #{user.userRole})")
    void addUser(@Param("user") User user);

    @Delete("DELETE FROM User WHERE user_account = #{userAccount}")
    void deleteUserByAccount(String userAccount);

    @Update("UPDATE User SET user_password = #{user.userPassword}, user_email =#{user.userEmail}, user_role = #{user.userRole} WHERE user_account = #{user.userAccount}")
    void updateUser(@Param("user") User user);


}
