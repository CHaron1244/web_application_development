package com.friend.mapper;

import com.friend.entity.Cart;
import com.friend.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@Mapper
public interface cartMapper {
    @Select("SELECT * FROM Cart")
    List<Cart> selectAllItemsInCart();

    @Select("SELECT * FROM Cart WHERE user_account=#{userAccount}")
    List<Cart> selectItemByUserAccount(String userAccount);

    @Insert("INSERT INTO Cart VALUES(#{cart.userAccount},#{cart.goodName},#{cart.goodPrice},#{cart.goodNumber})")
    void addItem(@Param("cart") Cart cart);

    @Delete("DELETE FROM Cart WHERE user_account=#{userAccount} AND good_name=#{goodName}")
    void deleteItemByUserAccountAndGoodName(String userAccount, String goodName);

}
