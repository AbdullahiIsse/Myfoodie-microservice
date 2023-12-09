package com.myfoodie.followerservice.feign;

import com.myfoodie.followerservice.config.CustomFeignConfiguration;

import com.myfoodie.followerservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "USER-SERVICE",configuration = CustomFeignConfiguration.class)
public interface UserServiceClient {
    @GetMapping("/api/user/exist/{userId}")
    boolean checkIfUserExists(@PathVariable("userId") String userId);

    @GetMapping("/api/user/{id}")
    UserResponse getUserById(@PathVariable("id") String id);
}
