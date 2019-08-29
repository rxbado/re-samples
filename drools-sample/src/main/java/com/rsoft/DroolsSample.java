package com.rsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.rsoft.**.dao")
@SpringBootApplication
public class DroolsSample {

    public static void main(String[] args) {
        SpringApplication.run(DroolsSample.class, args);
    }

}
