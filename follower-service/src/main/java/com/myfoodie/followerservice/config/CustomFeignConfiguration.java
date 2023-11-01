package com.myfoodie.followerservice.config;

import com.myfoodie.followerservice.utils.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class CustomFeignConfiguration {


    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }


}
