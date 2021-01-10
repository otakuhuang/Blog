package com.otakuhuang.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 指定这是一个 Spring Boot 的应用程序，
 * 相当于 @Configuration + @ EnableAutoConfiguration + @ComponentScan
 */
@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        // SpringApplication 用于从 main 方法启动 Spring 应用的类
        SpringApplication.run(BlogApplication.class, args);
    }

}
