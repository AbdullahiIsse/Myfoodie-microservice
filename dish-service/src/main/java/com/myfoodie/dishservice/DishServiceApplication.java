package com.myfoodie.dishservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "MyFoodie - Dish-Service Api"))
public class DishServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DishServiceApplication.class, args);
    }

}
