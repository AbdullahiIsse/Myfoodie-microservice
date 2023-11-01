package com.myfoodie.followerservice.service;

import com.myfoodie.followerservice.dto.FollowerRequest;
import com.myfoodie.followerservice.dto.FollowerResponse;
import com.myfoodie.followerservice.model.Follower;
import jakarta.validation.Valid;

import java.util.List;

public interface FollowerService {

    List<FollowerResponse> getFollowerFavoriteDishList(String followerId);
    void follow(@Valid FollowerRequest followerRequest) ;

    void deleteFollowerByIds(String followedId,String userId);

}
