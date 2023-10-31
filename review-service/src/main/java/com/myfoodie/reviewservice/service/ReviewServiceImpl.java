package com.myfoodie.reviewservice.service;

import com.myfoodie.reviewservice.dto.ReviewDishResponse;
import com.myfoodie.reviewservice.dto.ReviewRequest;
import com.myfoodie.reviewservice.dto.ReviewResponse;
import com.myfoodie.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public void createReview(ReviewRequest reviewRequest) {

    }

    @Override
    public void updateReview(long reviewId, ReviewRequest reviewRequest) {

    }

    @Override
    public void deleteReview(long reviewId) {

    }

    @Override
    public ReviewResponse getReviewById(long reviewId) {
        return null;
    }

    @Override
    public List<ReviewResponse> getReviewsByDish(long dishId) {
        return null;
    }

    @Override
    public List<ReviewResponse> getReviewsByUser(String userId) {
        return null;
    }

    @Override
    public double getAverageRatingForDish(long dishId) {
        return 0;
    }

    @Override
    public List<ReviewResponse> getLatestReviews(int count) {
        return null;
    }

    @Override
    public List<ReviewDishResponse> getTopRatedDishes(int count) {
        return null;
    }

    @Override
    public boolean hasUserReviewedDish(String userId, long dishId) {
        return false;
    }
}
