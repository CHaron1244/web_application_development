package com.friend.mapper;

import com.friend.entity.Good;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface goodMapper {
    @Select("SELECT * FROM good")
    List<Good> selectAllGoods();

    @Select("SELECT * FROM good WHERE good_name =#{goodName}")
    Good selectByName(String goodName);

    @Insert("INSERT INTO good VALUES(#{good.goodName}, #{good.goodPrice}, #{good.goodImg}, #{good.goodSale}, #{good.goodInfo}, #{good.goodType})")
    void addGood(@Param("good") Good good);

    @Delete("DELETE FROM good WHERE good_name = #{goodName}")
    void deleteGoodByName(String goodName);

    @Update("UPDATE Good SET good_price = #{good.goodPrice},good_img=#{good.goodImg},good_sale=#{good.goodSale}," +
            "good_info=#{good.goodInfo},good_type=#{good.goodType} WHERE good_name =#{good.goodName}")
    void updateGood(@Param("good") Good good);

    @Update("UPDATE Good SET good_sale = good_sale + #{goodNumber} WHERE good_name = #{goodName}")
    void updateGoodByGoodName(@Param("goodName") String goodName, @Param("goodNumber") Integer goodNumber);
}
