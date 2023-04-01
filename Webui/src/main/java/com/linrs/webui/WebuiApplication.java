package com.linrs.webui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.linrs.webui.controller"})//扫描注解controller，可以添加多个包地址，
@MapperScan("com.linrs.webui.mapper")
public class WebuiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebuiApplication.class, args);
    }
}
