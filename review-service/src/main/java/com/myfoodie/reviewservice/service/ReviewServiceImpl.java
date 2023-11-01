package com.myfoodie.reviewservice.service;

import com.myfoodie.reviewservice.dto.ReviewDishResponse;
import com.myfoodie.reviewservice.dto.ReviewRequest;
import com.myfoodie.reviewservice.dto.ReviewResponse;
import com.myfoodie.reviewservice.exception.ReviewNotFoundException;
import com.myfoodie.reviewservice.feign.UserServiceClient;
import com.myfoodie.reviewservice.model.Review;
import com.myfoodie.reviewservice.repository.ReviewRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.reverse;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserServiceClient userServiceClient;

    @Override
    @Transactional
    public void createReview(@Valid ReviewRequest reviewRequest) {
        Review review = Review.builder()
                .userId(reviewRequest.getUserId())
                .dishId(reviewRequest.getDishId())
                .rating(reviewRequest.getRating())
                .title(reviewRequest.getTitle())
                .comment(reviewRequest.getComment())
                .dateCreated(reviewRequest.getDateCreated())
                .build();
        reviewRepository.save(review);

        log.info("Review id {} is saved", review.getId());
    }

    @Override
    @Transactional
    public void updateReview(String userId, long reviewId, @Valid ReviewRequest reviewRequest) {
        Review review = reviewRepository.findReviewByIdAndUserId(reviewId, userId);
        if (review != null) {
            if (reviewRequest.getRating() != 0) {
                review.setRating(reviewRequest.getRating());
            }

            if (reviewRequest.getTitle() != null) {
                review.setTitle(reviewRequest.getTitle());
            }

            if (reviewRequest.getComment() != null) {
                review.setComment(reviewRequest.getComment());
            }

            review.setDateCreated(LocalDate.now());

            reviewRepository.save(review);

            log.info("Review {} has been updated", review.getId());
        } else {
            log.error("Review with id {} not found", reviewId);
            throw new ReviewNotFoundException("Review not found with id " + reviewId);
        }
    }

    @Override
    @Transactional
    public void deleteReview(long reviewId, String userId) {
        reviewRepository.deleteByIdAndUserId(reviewId, userId);
        log.info("Review id {} is removed", reviewId);
    }

    @Override
    public List<ReviewResponse> getReviewsByDish(long dishId) {
        var reviews = reviewRepository.findReviewsByDishId(dishId);

        return reviews.stream()
                .map(this::mapToReviewResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> getReviewsByUser(String userId) {
        var reviewsByUser = reviewRepository.findReviewsByUserId(userId);

        return reviewsByUser.stream()
                .map(this::mapToReviewResponse)
                .toList();
    }

    @Override
    public double getAverageRatingForDish(long dishId) {
        List<Review> reviews = reviewRepository.findReviewsByDishId(dishId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        double totalRating = reviews.stream().mapToDouble(Review::getRating).sum();
        return totalRating / reviews.size();
    }

    @Override
    public List<ReviewResponse> getLatestReviewsByDishId(long dishId) {
        List<Review> reviews = reviewRepository.findReviewsByDishId(dishId);
        reverse(reviews);

        return reviews.stream()
                .map(this::mapToReviewResponse)
                .limit(5)
                .toList();
    }


    private ReviewResponse mapToReviewResponse(Review review){
        return ReviewResponse.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .dishId(review.getDishId())
                .rating(review.getRating())
                .title(review.getTitle())
                .comment(review.getComment())
                .dateCreated(review.getDateCreated())
                .username(userServiceClient.getUserById(review.getUserId()).getUsername())
                .build();
    }


}
