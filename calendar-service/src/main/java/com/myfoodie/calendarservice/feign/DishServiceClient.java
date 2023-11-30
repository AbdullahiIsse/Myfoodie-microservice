package com.myfoodie.calendarservice.feign;

import com.myfoodie.calendarservice.dto.DishResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DISH-SERVICE",url = "https://myfoodie-dish-service.azurewebsites.net")
public interface DishServiceClient {
    @GetMapping("/api/dish/dish/{id}")
    DishResponse getDishesById(@PathVariable long id);
}
