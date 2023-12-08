package com.myfoodie.reviewservice.repository;

import com.myfoodie.reviewservice.model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {


    void deleteByIdAndUserId (long id,String userId);

    Review findReviewByIdAndUserId(long id,String userId);

    List<Review>findReviewsByDishId(long dishId);

    List<Review>findReviewsByUserId(String userId);

    List<Review>findReviewsByUserIdAndDishId(String userId,long dishId);

    @Query("SELECT r.dishId, COUNT(r) FROM review r GROUP BY r.dishId ORDER BY COUNT(r) DESC")
    List<Object[]> findDishesWithMostReviews();

    @Query("SELECT r.dishId, AVG(r.rating) FROM review r GROUP BY r.dishId ORDER BY AVG(r.rating) DESC")
    List<Object[]> findDishesWithHighestRating();






}
