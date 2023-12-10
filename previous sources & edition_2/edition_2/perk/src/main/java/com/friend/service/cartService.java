package com.friend.service;
import com.friend.entity.Cart;
import com.friend.mapper.cartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cartService {
    private final cartMapper cartmapper;

    @Autowired
    public cartService(cartMapper cartmapper) {
        this.cartmapper = cartmapper;
    }


    public List<Cart> selectAllItemsInCart() {
        return cartmapper.selectAllItemsInCart();
    }

    public List<Cart> selectItemByUserAccount(String userAccount) {
        return cartmapper.selectItemByUserAccount(userAccount);
    }

    public void addItem(Cart cart) {
        cartmapper.addItem(cart);
    }

    public void deleteItemByUserAccountAndGoodName(String userAccount, String goodName) {
        cartmapper.deleteItemByUserAccountAndGoodName(userAccount, goodName);
    }
}
