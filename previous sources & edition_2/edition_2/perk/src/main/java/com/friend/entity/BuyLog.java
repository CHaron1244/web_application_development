package com.friend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BuyLog {
    private String userAccount;
    private String goodName;
    private Integer goodNumber;//卖了多少
    private String buyTime;
}
