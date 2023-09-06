package com.llmcu.imooc.mybatisplus.basic.environment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.llmcu.imooc.mybatisplus.basic.environment.mapper")
public class
Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
