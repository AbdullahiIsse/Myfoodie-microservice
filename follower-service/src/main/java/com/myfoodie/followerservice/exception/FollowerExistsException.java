package com.myfoodie.followerservice.exception;

public class FollowerExistsException extends RuntimeException{

    public FollowerExistsException(String message) {
        super(message);
    }
}
