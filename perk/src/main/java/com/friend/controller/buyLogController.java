package com.friend.controller;

import com.friend.config.Result;
import com.friend.entity.BrowseLog;
import com.friend.entity.BuyLog;
import com.friend.service.buyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/buyLog")
public class buyLogController {
    public final buyLogService buyLogservice;

    @Autowired
    public buyLogController(buyLogService buyLogservice) {
        this.buyLogservice = buyLogservice;
    }

    @RequestMapping("/selectAllBuyLogs")
    public Result<?> selectAllBuyLogs() {
        return Result.success(this.buyLogservice.selectAllBuyLogs());
    }

    @GetMapping("/selectBuyLogByUserAccount")// 目前只能在url后面以key=value方式传值
    public Result<?> selectBuyLogByUserAccount(@RequestParam("userAccount") String userAccount) {
        return Result.success(this.buyLogservice.selectBuyLogByUserAccount(userAccount));
    }

    @RequestMapping("/addBuyLog")// 目前只能在url后面以key=value方式传值
    public Result<?> addBuyLog(@RequestBody BuyLog log) { //post方式
        this.buyLogservice.addBuyLog(log);
        return Result.success();
    }

    @RequestMapping("/deleteBuyLogByUserAccount")
    public Result<?> deleteBuyLogByUserAccount(@RequestParam("userAccount") String userAccount) {
        this.buyLogservice.deleteBuyLogByUserAccount(userAccount);
        return Result.success();
    }

}
