package com.myfoodie.favoritedishservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "MyFoodie - Favorite-Dish-Service Api"))
@EnableFeignClients
public class FavoriteDishServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FavoriteDishServiceApplication.class, args);
    }

}
