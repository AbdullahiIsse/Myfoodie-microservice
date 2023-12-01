package com.myfoodie.favoritedishservice.feign;

import com.myfoodie.favoritedishservice.dto.DishResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("DISH-SERVICE")
public interface DishServiceClient {
    @GetMapping("/api/dish/id")
     List<DishResponse> getDishByIds(@RequestParam List<Long> id);
}
