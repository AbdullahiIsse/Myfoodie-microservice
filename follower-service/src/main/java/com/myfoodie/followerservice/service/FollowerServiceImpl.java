package com.myfoodie.followerservice.service;

import com.myfoodie.followerservice.dto.FavoriteDishResponse;
import com.myfoodie.followerservice.dto.FollowerRequest;
import com.myfoodie.followerservice.dto.FollowerResponse;
import com.myfoodie.followerservice.exception.FollowerExistsException;
import com.myfoodie.followerservice.exception.UserNotFoundException;
import com.myfoodie.followerservice.feign.FavoriteDishServiceClient;
import com.myfoodie.followerservice.feign.UserServiceClient;
import com.myfoodie.followerservice.model.Follower;
import com.myfoodie.followerservice.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowerServiceImpl implements FollowerService {

    private final FollowerRepository followerRepository;
    private final FavoriteDishServiceClient favoriteDishServiceClient;
    private final UserServiceClient userServiceClient;


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

        if (!userServiceClient.checkIfUserExists(follower.getFollowerId())) {
            throw new UserNotFoundException("The user with ID " + follower.getFollowerId() + " does not exists");
        }

        if (!userServiceClient.checkIfUserExists(follower.getUserId())) {
            throw new UserNotFoundException("The user with ID " + follower.getUserId() + " does not exists");
        }

        if (followerRepository.existsByFollowerIdAndUserId(follower.getFollowerId(), follower.getUserId())) {
            throw new FollowerExistsException("The follower with ID " + follower.getFollowerId() + " is already following the user with ID " + follower.getUserId());
        }

        if (follower.getFollowerId().equals(follower.getUserId())) {
            throw new FollowerExistsException("The user can not follow it self");
        }

        followerRepository.save(follower);

        log.info("Follower ID {} is now following user ID {}", follower.getFollowerId(), follower.getUserId());
    }


    @Override
    @Transactional
    public void deleteFollowerByIds(String followedId, String userId) {
        if (!userServiceClient.checkIfUserExists(followedId)) {
            throw new UserNotFoundException("The user with ID " + followedId + " does not exists");
        }

        if (!userServiceClient.checkIfUserExists(userId) ){
            throw new UserNotFoundException("The user with ID " + userId + " does not exists");
        }

        if (followerRepository.existsByFollowerIdAndUserId(followedId, userId)) {
            followerRepository.deleteByFollowerIdAndUserId(followedId, userId);
            log.info("User id {} is no longer following user id {}", userId, followedId);
        }
    }
}
