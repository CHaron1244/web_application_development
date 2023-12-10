package com.friend.service;

import com.friend.entity.Good;
import com.friend.mapper.goodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class goodService {
    private final goodMapper goodmapper;

    @Autowired
    public goodService(goodMapper goodmapper) {
        this.goodmapper = goodmapper;
    }

    public List<Good> selectAllGoods() {
        return this.goodmapper.selectAllGoods();
    }

    public Good selectGoodByName(String goodName) {
        return this.goodmapper.selectByName(goodName);
    }

    public void addGood(Good good) {
        this.goodmapper.addGood(good);
    }

    public void updateGoodByGoodName(String goodName, Integer goodNumber) {
        this.goodmapper.updateGoodByGoodName(goodName, goodNumber);
    }

    public void deleteGoodByName(String goodName) {
        this.goodmapper.deleteGoodByName(goodName);
    }


    public void updateGood(Good good) {
        this.goodmapper.updateGood(good);
    }


}
