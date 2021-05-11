package com.yaof;

import com.yaof.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

/**
 * springboot 启动文件
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:spring-mybatis.xml"})
public class GitDemoApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(GitDemoApplication.class);

    public static void main(String[] args) {
        System.setProperty("-Djava.awt.headless", "false");
        SpringApplication.run(GitDemoApplication.class, args);
        logger.info("SpringBoot started!!!");
    }

    /**
     * 打包war包，外部启动的方法
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GitDemoApplication.class);
    }

    @Bean
    @Scope("singleton")
    public User getUser(){
        return new User();
    }
}
