package com.friend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Good {
    private String goodName;
    private Double goodPrice;
    private String goodImg;
    private Integer goodSale;
    private String goodInfo;
    private String goodType;
}
