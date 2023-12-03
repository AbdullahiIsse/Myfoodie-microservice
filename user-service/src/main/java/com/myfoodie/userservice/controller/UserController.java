package com.myfoodie.userservice.controller;

import com.myfoodie.userservice.dto.UserRequest;
import com.myfoodie.userservice.dto.UserResponse;
import com.myfoodie.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/other/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllTheOtherUsers(@PathVariable String id) {
        return userService.getAllOtherUsers(id);
    }


    @GetMapping("/exist/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkIfUserExists(@PathVariable("userId") String userId) {
        return userService.checkIfUserExists(userId);
    }

    @GetMapping("/exist/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkIfUserExistByEmail(@PathVariable("email") String email) {
        return userService.checkIfUserExistByEmail(email);
    }
}
