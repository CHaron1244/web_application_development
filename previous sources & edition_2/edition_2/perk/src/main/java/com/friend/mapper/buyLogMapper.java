package com.friend.mapper;

import com.friend.entity.BrowseLog;
import com.friend.entity.BuyLog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface buyLogMapper {
    @Select("SELECT * FROM buyLog")
    List<BuyLog> selectAllBuyLogs();

    @Select("SELECT * FROM buyLog WHERE user_account=#{userAccount}")
    List<BuyLog> selectBuyLogByUserAccount(String userAccount);

    @Insert("INSERT INTO buyLog (user_account, good_name, good_number, buy_time) VALUES(#{log.userAccount}, #{log.goodName}, #{log.goodNumber}, #{log.buyTime})")
    void addBuyLog(@Param("log") BuyLog log);


    @Delete("DELETE FROM buyLog WHERE user_account=#{userAccount}")
    void deleteBuyLogByUserAccount(String userAccount);
}
