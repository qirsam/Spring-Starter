package com.qirsam.spring.config;

import com.qirsam.spring.config.condition.JpaConditional;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Conditional(JpaConditional.class)
public class JpaConfiguration {

    @PostConstruct
    void init() {
        System.out.println("Jpa Enabled");
    }
}
