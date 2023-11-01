package com.myfoodie.reviewservice.service;

import com.myfoodie.reviewservice.dto.ReviewDishResponse;
import com.myfoodie.reviewservice.dto.ReviewRequest;
import com.myfoodie.reviewservice.dto.ReviewResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ReviewService {

    void createReview(@Valid ReviewRequest reviewRequest);
    void updateReview(String userId,long reviewId,@Valid  ReviewRequest reviewRequest);
    void deleteReview(long reviewId,String userId);

    List<ReviewResponse> getReviewsByDish(long dishId);

    List<ReviewResponse> getReviewsByUser(String userId);

    double getAverageRatingForDish(long dishId);

    List<ReviewResponse> getLatestReviewsByDishId(long dishId);




}
