package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan // 扫描Servlet
@SpringBootApplication//启动类
@EnableTransactionManagement // 开启事务管理
@EnableCaching  //开启缓存注解功能
@Slf4j
public class Demo10Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo10Application.class, args);
        log.info("启动成功");
    }

}
