package com.zz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@MapperScan("com.zz.mapper")
@SpringBootApplication
public class zzApplication {

    public static void main(String[] args) {
        SpringApplication.run(zzApplication.class, args);
    }

}
