package com.myfoodie.followerservice.feign;

import com.myfoodie.followerservice.dto.FavoriteDishResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient("FAVORITE-DISH-SERVICE")
public interface FavoriteDishServiceClient {
    @GetMapping("/api/favorite/{id}")
   List<FavoriteDishResponse> getFavoriteDishesByUserId(@PathVariable String id);
}
