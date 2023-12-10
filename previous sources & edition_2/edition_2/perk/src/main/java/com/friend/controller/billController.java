package com.friend.controller;

import com.friend.config.Result;
import com.friend.entity.Bill;
import com.friend.service.billService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bill")
public class billController {
    public final billService billservice;

    @Autowired
    public billController(billService billservice) {
        this.billservice = billservice;
    }

    @RequestMapping("/selectAllBills")
    public Result<?> selectAllBills() {
        return Result.success(this.billservice.selectAllBills());
    }

    @RequestMapping("/selectBillByUserAccount")
    public Result<?> selectBillByUserAccount(@RequestParam("userAccount") String userAccount) {
        return Result.success(this.billservice.selectBillByUserAccount(userAccount));
    }

    @RequestMapping("/addBill")
    public Result<?> addBill(@RequestBody Bill bill) {
        this.billservice.addBill(bill);
        return Result.success();
    }

    @RequestMapping("/deleteBillByUserAccount")
    public Result<?> deleteBillByUserAccount(@RequestParam("userAccount") String userAccount) {
        this.billservice.deleteBillByUserAccount(userAccount);
        return Result.success();
    }
}
