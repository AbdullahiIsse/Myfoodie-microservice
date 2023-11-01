package com.myfoodie.followerservice.service;

import com.myfoodie.followerservice.dto.FavoriteDishResponse;
import com.myfoodie.followerservice.dto.FollowerRequest;
import com.myfoodie.followerservice.dto.FollowerResponse;
import com.myfoodie.followerservice.feign.FavoriteDishServiceClient;
import com.myfoodie.followerservice.feign.UserServiceClient;
import com.myfoodie.followerservice.model.Follower;
import com.myfoodie.followerservice.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowerServiceImpl implements FollowerService{

    private final FollowerRepository followerRepository;
    private final FavoriteDishServiceClient favoriteDishServiceClient;


    @Override
    public List<FollowerResponse> getFollowerFavoriteDishList(String followerId) {
        List<Follower> followers = followerRepository.findAllByFollowerId(followerId);

        return followers.stream()
                .map(follower -> {
                    List<FavoriteDishResponse> favoriteDishes = favoriteDishServiceClient.getFavoriteDishesByUserId(follower.getUserId());

                    return FollowerResponse.builder()
                            .followerId(follower.getFollowerId())
                            .userId(follower.getUserId())
                            .followedDate(follower.getFollowedDate())
                            .favoriteDishResponses(favoriteDishes)
                            .build();
                })
                .toList();
    }

    @Override
    @Transactional
    public void follow(FollowerRequest followerRequest) {
        Follower follower = Follower.builder()
                .followerId(followerRequest.getFollowerId())
                .userId(followerRequest.getUserId())
                .followedDate(followerRequest.getFollowedDate())
                .build();

        followerRepository.save(follower);

        log.info("Follower id {} is saved", follower.getId());
    }


    @Override
    @Transactional
    public void deleteFollowerByIds(String followedId,String userId) {
        followerRepository.deleteByFollowerIdAndUserId(followedId, userId);

        log.info("User id {} is no longer following user id {}", userId,followedId);
    }

}
