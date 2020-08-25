package com.vim;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.vim.modules.*.dao")
@EnableCaching
@ServletComponentScan
public class WebsiteApplication {

    public static void main( String[] args ) {
        SpringApplication.run(WebsiteApplication.class, args);
    }

}
