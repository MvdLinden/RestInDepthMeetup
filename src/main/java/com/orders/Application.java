package com.orders;

import com.orders.domain.Order;
import com.orders.repositories.OrderMemoryRepository;
import com.orders.repositories.OrderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by marco on 03/11/14.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    @Bean
    public OrderRepository createRepository() {
        return new OrderMemoryRepository();
    }


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        /* System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }  */
    }
}
