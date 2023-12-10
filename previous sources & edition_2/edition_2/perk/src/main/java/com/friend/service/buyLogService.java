package com.friend.service;

import com.friend.entity.BrowseLog;
import com.friend.entity.BuyLog;
import com.friend.mapper.buyLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class buyLogService {
    public  final buyLogMapper buylogmapper;

    @Autowired
    public buyLogService(buyLogMapper buyLogmapper) {
        this.buylogmapper = buyLogmapper;
    }

    public List<BuyLog> selectAllBuyLogs() {
        return buylogmapper.selectAllBuyLogs();
    }

    public List<BuyLog> selectBuyLogByUserAccount(String userAccount) {
        return buylogmapper.selectBuyLogByUserAccount(userAccount);
    }

    public void addBuyLog(BuyLog log) {
        buylogmapper.addBuyLog(log);
    }

    public void deleteBuyLogByUserAccount(String userAccount) {
        buylogmapper.deleteBuyLogByUserAccount(userAccount);
    }
}
