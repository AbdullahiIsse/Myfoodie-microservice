package com.myfoodie.userservice.service;

import com.myfoodie.userservice.dto.UserRequest;
import com.myfoodie.userservice.dto.UserResponse;
import com.myfoodie.userservice.model.User;
import com.myfoodie.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    public void createUser(@Valid UserRequest userRequest) {
        User user = User.builder()
                .id(userRequest.getId())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .lastSeen(userRequest.getLastSeen())
                .build();

        userRepository.save(user);
    }

    @Override
    public UserResponse getUserById(String id) {
        var user = userRepository.findUserById(id);
        return UserResponse
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .lastSeen(user.getLastSeen())
                .build();
    }


    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    @Override
    public boolean checkIfUserExists(String userId) {
        return userRepository.existsUserById(userId);
    }


    private UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .lastSeen(user.getLastSeen())
                .build();
    }
}
