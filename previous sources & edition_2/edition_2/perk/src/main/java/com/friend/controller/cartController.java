package com.friend.controller;

import com.friend.config.Result;
import com.friend.entity.Cart;
import com.friend.service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class cartController {
    private final cartService cartservice;

    @Autowired
    public cartController(cartService cartservice) {
        this.cartservice = cartservice;
    }

    @RequestMapping("/checkout")//支付完跳转到bill,同时buyLog中也会有记录
    public Result<?> checkout(String userAccount) { // 用于结账,传参是用户名
        List<Cart> cartList = cartservice.selectAllItemsInCart();
        double check = 0.0;
        for (Cart cart : cartList) {
            if (cart.getUserAccount().equals(userAccount)) {
                check += cart.getGoodPrice() * cart.getGoodNumber();
            }
        }
        // 在此进行账号支付
        return Result.success();
    }

    @RequestMapping("/selectAllItemsInCart")
    public Result<?> selectAllItems() {
        return Result.success(this.cartservice.selectAllItemsInCart());
    }

    @RequestMapping("/selectItemByUserAccount/{userAccount}")
    public Result<?> selectItemByUserAccount(@PathVariable("userAccount") String userAccount) {
        return Result.success(this.cartservice.selectItemByUserAccount(userAccount));
    }

    @RequestMapping("/addItem")
    public Result<?> addItem(@RequestBody Cart cart) {
        this.cartservice.addItem(cart);
        return Result.success();
    }

    @RequestMapping("/deleteItemByUserAccountAndGoodName/{userAccount}/{goodName}")
    public Result<?> deleteItemByUserAccountAndGoodName(@PathVariable String userAccount, @PathVariable String goodName) {
        this.cartservice.deleteItemByUserAccountAndGoodName(userAccount, goodName);
        return Result.success();
    }
}
