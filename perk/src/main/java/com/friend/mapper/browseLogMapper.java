package com.friend.mapper;

import com.friend.entity.BrowseLog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface browseLogMapper {
    @Select("SELECT * FROM browseLog")
    List<BrowseLog> selectAllBrowseLogs();

    @Select("SELECT * FROM browseLog WHERE user_account = #{userAccount}")
    List<BrowseLog> selectBrowseLogByUserAccount(@Param("userAccount") String userAccount);

    @Insert("INSERT INTO browseLog(user_account, good_name, browse_time) VALUES (#{log.userAccount}, #{log.goodName},#{log.browseTime})")
    void addBrowseLog(@Param("log") BrowseLog log);

    @Delete("DELETE FROM browseLog where user_account = #{userAccount} ")
    void deleteBrowseLogByUserAccount(@Param("userAccount") String userAccount);
}
