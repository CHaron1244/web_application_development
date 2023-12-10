package com.friend.service;

import com.friend.entity.BrowseLog;
import com.friend.mapper.browseLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class browseLogService {
    @Autowired
    private browseLogMapper browseLogmapper;

    public List<BrowseLog> selectAllBrowseLogs() {
        return browseLogmapper.selectAllBrowseLogs();
    }

    public List<BrowseLog> selectBrowseLogByUserAccount(String userAccount) {
        return browseLogmapper.selectBrowseLogByUserAccount(userAccount);
    }

    public void addBrowseLog(BrowseLog log) {
        browseLogmapper.addBrowseLog(log);
    }

    public void deleteBrowseLogByUserAccount(String userAccount) {
        browseLogmapper.deleteBrowseLogByUserAccount(userAccount);
    }
}
