package com.myfoodie.userservice.service;

import com.myfoodie.userservice.dto.UserRequest;
import com.myfoodie.userservice.dto.UserResponse;
import com.myfoodie.userservice.model.User;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    void createUser( @Valid UserRequest userRequeste);

    UserResponse getUserById(String id);

    List<UserResponse> getAllUsers();

    List<UserResponse> getAllOtherUsers(String userId);

    boolean checkIfUserExists (String userId);


}
