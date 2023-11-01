package com.myfoodie.followerservice.utils;

import com.myfoodie.followerservice.exception.UserNotFoundException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 500) {
            return new UserNotFoundException("The user service is currently unavailable");
        }
        return FeignException.errorStatus(s, response);
    }
}
