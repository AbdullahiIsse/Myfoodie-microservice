package com.myfoodie.followerservice.repository;

import com.myfoodie.followerservice.model.Follower;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FollowerRepository extends CrudRepository<Follower,Long> {

    List<Follower> findAllByFollowerId(String followerID);
    void deleteByFollowerIdAndUserId(String followerId,String userId);

    boolean existsByFollowerIdAndUserId (String followerId,String userId);

    List<Follower> findAllByUserId(String userId);






}
