package com.myfoodie.followerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FollowerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FollowerServiceApplication.class, args);
    }

}
