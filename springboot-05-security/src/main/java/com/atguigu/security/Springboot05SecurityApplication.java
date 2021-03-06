package com.atguigu.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1，引入security
 * 2，编写配置类extends WebSecurityConfigurerAdapter @EnableWebSecurity
 * 3, 控制请求访问权限
 */
@SpringBootApplication
public class Springboot05SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot05SecurityApplication.class, args);
    }

}
