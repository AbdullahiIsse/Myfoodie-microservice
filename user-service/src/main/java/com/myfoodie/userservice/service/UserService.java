package com.myfoodie.userservice.service;

import com.myfoodie.userservice.dto.UserRequest;
import com.myfoodie.userservice.dto.UserResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    void createUser( @Valid UserRequest userRequeste);

    List<UserResponse> getAllUsers();


}
