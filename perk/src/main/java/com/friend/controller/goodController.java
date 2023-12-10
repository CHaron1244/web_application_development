package com.friend.controller;

import com.friend.config.Result;
import com.friend.entity.Good;
import com.friend.service.goodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/good")
public class goodController {
    private final goodService goodservice;

    @Autowired
    public goodController(goodService goodService) {
        this.goodservice = goodService;
    }

    @RequestMapping("/selectAllGoods")
    public Result<?> selectAllGoods(){
        return Result.success(goodservice.selectAllGoods());
    }

    @RequestMapping("/selectGoodByName/{goodName}")
    public Result<?> selectGoodByName(@PathVariable String goodName){
        return Result.success(goodservice.selectGoodByName(goodName));
    }

    @RequestMapping("/addGood")
    public Result<?> addGood(@RequestBody Good good){
        goodservice.addGood(good);
        return Result.success();
    }

    @RequestMapping("/deleteGoodByName/{goodName}")
    public Result<?> deleteGoodByName(@PathVariable String goodName){
        goodservice.deleteGoodByName(goodName);
        return Result.success();
    }

    @RequestMapping("/updateGood")
    public Result<?> updateGood(@RequestBody Good good){
        goodservice.updateGood(good);
        return Result.success();
    }

    @RequestMapping("/updateGoodByGoodName/{goodName}/{goodNumber}")
    public Result<?> updateGoodByGoodName(@PathVariable("goodName") String goodName,@PathVariable("goodNumber") Integer goodNumber){
        goodservice.updateGoodByGoodName(goodName,goodNumber);
        return Result.success();
    }

    @RequestMapping("/getTotalIncome")
    public Result<?> getTotalIncome(){
        List<Good> goods = goodservice.selectAllGoods();
        Double income = 0.0;
        for(Good good : goods) {
            income += good.getGoodSale()*good.getGoodPrice();
        }
        System.out.println("income = " + income);
        return Result.success(income);
    }
}
