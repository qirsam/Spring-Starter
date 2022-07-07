package com.qirsam.spring.config;

import com.qirsam.spring.database.pool.ConnectionPool;
import com.qirsam.spring.database.repository.CrudRepository;
import com.qirsam.spring.database.repository.UserRepository;
import com.qirsam.spring.service.UserService;
import com.qirsam.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.Filter;

//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.qirsam.spring",
        useDefaultFilters = false,
        includeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = Component.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
                @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")

        }
)
public class ApplicationConfiguration {

    @Bean
    @Profile("")
    public ConnectionPool pool2(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 25);
    }

    @Bean
    public UserRepository userRepository2(ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    @Bean
    public UserRepository userRepository3() {
        var connectionPool1 = pool3();
        var connectionPool2 = pool3();
        var connectionPool3 = pool3();
        return new UserRepository(pool3());
    }
}
