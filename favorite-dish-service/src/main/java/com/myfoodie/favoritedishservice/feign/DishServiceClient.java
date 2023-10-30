package com.myfoodie.favoritedishservice.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("DISH-SERVICE")
public interface DishServiceClient {
}
