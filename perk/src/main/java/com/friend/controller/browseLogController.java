package com.friend.controller;

import com.friend.config.Result;
import com.friend.entity.BrowseLog;
import com.friend.service.browseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/browseLog")
public class browseLogController {
    private final browseLogService browseLogservice;

    @Autowired
    public browseLogController(browseLogService browseLogservice) {
        this.browseLogservice = browseLogservice;
    }

    @RequestMapping("/selectAllBrowseLogs")
    public Result<?> selectAllBrowseLogs() {
        return Result.success(this.browseLogservice.selectAllBrowseLogs());
    }

    @RequestMapping("/selectBrowseLogByUserAccount")// 目前只能在url后面以key=value方式传值 get<===>@RequestParam
    public Result<?> selectBrowseLogByUserAccount(@RequestParam("userAccount") String userAccount) {
        return Result.success(this.browseLogservice.selectBrowseLogByUserAccount(userAccount));
    }

    @RequestMapping("/addBrowseLog")
    public Result<?> addBrowseLog(@RequestBody BrowseLog log) {
        this.browseLogservice.addBrowseLog(log);
        return Result.success();
    }

    @RequestMapping("/deleteBrowseLogByUserAccount")
    public Result<?> deleteBrowseLogByUserAccount(@RequestParam("userAccount") String userAccount) {
        this.browseLogservice.deleteBrowseLogByUserAccount(userAccount);
        return Result.success();
    }
}
