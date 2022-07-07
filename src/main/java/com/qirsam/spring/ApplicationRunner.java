package com.qirsam.spring;

import com.qirsam.spring.config.ApplicationConfiguration;
import com.qirsam.spring.database.pool.ConnectionPool;
import com.qirsam.spring.service.CompanyService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.Serializable;

public class ApplicationRunner {

    public static void main(String[] args) {
        String value = "hello";
        System.out.println(CharSequence.class.isAssignableFrom(value.getClass()));
        System.out.println(BeanFactoryPostProcessor.class.isAssignableFrom(value.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(value.getClass()));

        try (var context = new AnnotationConfigApplicationContext( ApplicationConfiguration.class)) {
            var pool2 = context.getBean("pool1", ConnectionPool.class);
            System.out.println(pool2);

            var companyService = context.getBean(CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}
