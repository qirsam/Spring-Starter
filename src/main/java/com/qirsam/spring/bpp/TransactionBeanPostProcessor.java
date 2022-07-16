package com.qirsam.spring.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class TransactionBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> transactionBeans = new HashMap<>(); //сохраняем метаинформацию о нашем изначальном бине, если у него есть аннотация

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Transaction.class)) {
            transactionBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = transactionBeans.get(beanName); //Проверяем по метаинформации соответствует ли пришедший бин изначальному (т.к. мог прийти прокси с другого afterBPP)
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                System.out.println("Open transaction");
                try {
                    return method.invoke(bean, args); //обрабатываем пришедший бин, если это был прокси, то один прокси оборачиваем в другой
                } catch (Exception exception) {
                    System.out.println("rollback transaction");
                    throw exception;
                }
                finally {
                    System.out.println("Close transaction");
                }
            });
        }
        return bean;
    }
}
