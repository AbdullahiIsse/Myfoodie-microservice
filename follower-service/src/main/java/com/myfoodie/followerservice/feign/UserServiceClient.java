package com.myfoodie.followerservice.feign;

import com.myfoodie.followerservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("USER-SERVICE")
public interface UserServiceClient {
    @GetMapping("/api/user/{id}")
    UserResponse getUserById(@PathVariable("id") String id);
}
