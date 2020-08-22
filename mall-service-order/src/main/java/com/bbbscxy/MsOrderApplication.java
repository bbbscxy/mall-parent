package com.bbbscxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @作者 Administrator
 * @时间 2020-08-20 16:13
 * @版本 1.0
 * @说明
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.bbbscxy.modules.mapper"})
public class MsOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsOrderApplication.class, args);
    }
}
