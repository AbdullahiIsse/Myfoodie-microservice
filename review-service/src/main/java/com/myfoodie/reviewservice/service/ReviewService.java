package com.myfoodie.reviewservice.service;

import com.myfoodie.reviewservice.dto.ReviewDishResponse;
import com.myfoodie.reviewservice.dto.ReviewRequest;
import com.myfoodie.reviewservice.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {

    void createReview(ReviewRequest reviewRequest);
    void updateReview(long reviewId, ReviewRequest reviewRequest);
    void deleteReview(long reviewId);

    ReviewResponse getReviewById(long reviewId);

    List<ReviewResponse> getReviewsByDish(long dishId);

    List<ReviewResponse> getReviewsByUser(String userId);

    double getAverageRatingForDish(long dishId);

    List<ReviewResponse> getLatestReviews(int count);


    List<ReviewDishResponse> getTopRatedDishes(int count);

    boolean hasUserReviewedDish(String userId, long dishId);


}
