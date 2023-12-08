package com.myfoodie.reviewservice.feign;

import com.myfoodie.reviewservice.dto.DishResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("DISH-SERVICE")
public interface DishServiceClient {
    @GetMapping("/api/dish/dish/{id}")
    DishResponse getDishesById(@PathVariable long id);
}
