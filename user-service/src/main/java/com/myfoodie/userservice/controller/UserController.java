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
}
