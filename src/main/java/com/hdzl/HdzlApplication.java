package com.hdzl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.hdzl.mapper")
@SpringBootApplication
public class HdzlApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdzlApplication.class, args);
    }

}
