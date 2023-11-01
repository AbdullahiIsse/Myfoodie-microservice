package com.myfoodie.followerservice.controller;

import com.myfoodie.followerservice.dto.FollowerRequest;
import com.myfoodie.followerservice.dto.FollowerResponse;
import com.myfoodie.followerservice.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follower")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void follow(@RequestBody FollowerRequest followerRequest) {
        followerService.follow(followerRequest);
    }

    @GetMapping("/{followerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FollowerResponse> getFollowerFavoriteDishList(@PathVariable String followerId) {
        return followerService.getFollowerFavoriteDishList(followerId);
    }

    @DeleteMapping("/{followedId}/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFollow(@PathVariable String followedId,@PathVariable String userId) {
        followerService.deleteFollowerByIds(followedId, userId);
    }


}
