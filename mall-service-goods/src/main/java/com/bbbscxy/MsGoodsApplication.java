package com.bbbscxy;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
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
@NacosPropertySource(dataId = "${spring.application.name}", autoRefreshed = true)
public class MsGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsGoodsApplication.class, args);
    }
}
