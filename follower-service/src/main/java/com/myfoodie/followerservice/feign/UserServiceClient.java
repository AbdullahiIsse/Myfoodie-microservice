package com.myfoodie.followerservice.feign;

import com.myfoodie.followerservice.config.CustomFeignConfiguration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "USER-SERVICE",configuration = CustomFeignConfiguration.class,url = "https://myfoodie-user-service.azurewebsites.net")
public interface UserServiceClient {
    @GetMapping("/api/user/exist/{userId}")
    boolean checkIfUserExists(@PathVariable("userId") String userId);
}
