package com.friend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Bill {
    private String userAccount;
    private String goodName;
    private Integer goodNumber;
    private String orderTime;
}
