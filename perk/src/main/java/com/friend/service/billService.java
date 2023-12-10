package com.friend.service;

import com.friend.entity.Bill;
import com.friend.mapper.billMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class billService {
    public final billMapper billmapper;

    @Autowired
    public billService(billMapper billmapper) {
        this.billmapper = billmapper;
    }

    public List<Bill> selectAllBills() {
        return billmapper.selectAllBills();
    }

    public List<Bill> selectBillByUserAccount(String userAccount) {
        return billmapper.selectBillByUserAccount(userAccount);
    }

    public void addBill(Bill bill) {
        billmapper.addBill(bill);
    }

    public void deleteBillByUserAccount(String userAccount) {
        billmapper.deleteBillByUserAccount(userAccount);
    }
}
