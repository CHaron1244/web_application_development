package com.friend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.friend.mapper")
public class PerkApplication {
	public static void main(String[] args) {
		SpringApplication.run(PerkApplication.class, args);
	}
}
