package com.myfoodie.reviewservice.feign;

import com.myfoodie.reviewservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "USER-SERVICE",url = "https://myfoodie-user-service.azurewebsites.net")
public interface UserServiceClient {
    @GetMapping("/api/user/{id}")
    UserResponse getUserById(@PathVariable("id") String id);
}
