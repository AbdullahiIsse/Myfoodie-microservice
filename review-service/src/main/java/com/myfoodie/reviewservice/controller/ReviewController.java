package com.myfoodie.reviewservice.controller;

import com.myfoodie.reviewservice.dto.ReviewDishResponse;
import com.myfoodie.reviewservice.dto.ReviewRequest;
import com.myfoodie.reviewservice.dto.ReviewResponse;
import com.myfoodie.reviewservice.exception.ReviewNotFoundException;
import com.myfoodie.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.createReview(reviewRequest);
    }

    @PatchMapping("/{userId}/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDish(@PathVariable String userId, @PathVariable Long reviewId, @RequestBody ReviewRequest reviewRequest) throws ReviewNotFoundException {
        reviewService.updateReview(userId, reviewId, reviewRequest);
    }


    @DeleteMapping("/{userId}/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDish(@PathVariable String userId, @PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId, userId);
    }


    @GetMapping("/{dishId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviewsByDish(@PathVariable long dishId) {
        return reviewService.getReviewsByDish(dishId);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviewsByUser(@PathVariable String userId) {
        return reviewService.getReviewsByUser(userId);
    }


    @GetMapping("/rating/{dishId}")
    @ResponseStatus(HttpStatus.OK)
    public double getAverageRatingForDish(@PathVariable long dishId) {
        return reviewService.getAverageRatingForDish(dishId);
    }


    @GetMapping("/latest/{dishId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getLatestReviewsByDishId(@PathVariable Long dishId) {
        return reviewService.getLatestReviewsByDishId(dishId);
    }


    @GetMapping("/user/{userId}/{dishId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviewsByUserAndDishId(@PathVariable String userId,@PathVariable Long dishId) {
        return reviewService.getReviewsByUserIdAndDishId(userId,dishId);
    }

    @GetMapping("/most-reviewed-dishes")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDishResponse> getDishesWithMostReviews() {
        return reviewService.getDishesWithMostReviews();
    }

    @GetMapping("/highest-rated-dishes")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDishResponse> getDishesWithHighestRating() {
        return reviewService.getDishesWithHighestRating();
    }


}
