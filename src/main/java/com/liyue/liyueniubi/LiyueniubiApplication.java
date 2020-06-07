package com.liyue.liyueniubi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackageClasses ="com.liyue.liyueniubi.mapper")
public class LiyueniubiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiyueniubiApplication.class, args);
    }

}
