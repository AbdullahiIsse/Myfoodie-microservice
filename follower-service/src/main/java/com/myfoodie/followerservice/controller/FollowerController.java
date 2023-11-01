package com.myfoodie.followerservice.controller;

import com.myfoodie.followerservice.dto.FollowerRequest;
import com.myfoodie.followerservice.dto.FollowerResponse;
import com.myfoodie.followerservice.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follower")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followerService;

    @PostMapping
    public ResponseEntity<String> follow(@RequestBody FollowerRequest followerRequest) {
        try {
            followerService.follow(followerRequest);
            return new ResponseEntity<>("Follower created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create follower: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{followerId}")
    public ResponseEntity<List<FollowerResponse>> getFollowerFavoriteDishList(@PathVariable String followerId) {
        List<FollowerResponse> followers = followerService.getFollowerFavoriteDishList(followerId);
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    @DeleteMapping("/{followedId}/{userId}")
    public ResponseEntity<String> removeFollow(@PathVariable String followedId, @PathVariable String userId) {
        try {
            followerService.deleteFollowerByIds(followedId, userId);
            return new ResponseEntity<>("Follower deleted successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete follower: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
