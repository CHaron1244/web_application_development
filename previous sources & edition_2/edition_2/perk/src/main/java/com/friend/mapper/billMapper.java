package com.friend.mapper;

import com.friend.entity.Bill;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface billMapper {

    @Select("SELECT * FROM bill")
    List<Bill> selectAllBills();

    @Select("SELECT * FROM bill WHERE user_account=#{userAccount}")
    List<Bill> selectBillByUserAccount(@Param("userAccount") String userAccount);

    @Delete("DELETE FROM bill WHERE user_account=#{userAccount}")
    void deleteBillByUserAccount(@Param("userAccount") String userAccount);

    @Insert("INSERT INTO bill VALUES(#{bill.userAccount},#{bill.goodName},#{bill.goodNumber},#{bill.orderTime})")
    void addBill(@Param("bill") Bill bill);
}
