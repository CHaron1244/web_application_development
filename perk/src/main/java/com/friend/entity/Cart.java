package com.friend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Cart {
    private String userAccount;
    private String goodName;
    private Double goodPrice;
    private Integer goodNumber;//加入了多少在购物车里面
}
