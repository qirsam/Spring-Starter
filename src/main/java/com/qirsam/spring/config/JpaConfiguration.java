package com.qirsam.spring.config;

import com.qirsam.spring.config.condition.JpaConditional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@Conditional(JpaConditional.class)
public class JpaConfiguration {

//    @Bean
//    @ConfigurationProperties(prefix = "db")
//    public DatabaseProperties databaseProperties() {
//        return new DatabaseProperties();
//    }

    @PostConstruct
    void init() {
        log.info("Jpa Enabled");
    }
}
