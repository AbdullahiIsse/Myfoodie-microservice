package com.myfoodie.reviewservice.repository;

import com.myfoodie.reviewservice.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {


    void deleteByIdAndUserId (long id,String userId);

    Review findReviewByIdAndUserId(long id,String userId);

    List<Review>findReviewsByDishId(long dishId);

    List<Review>findReviewsByUserId(String userId);






}
