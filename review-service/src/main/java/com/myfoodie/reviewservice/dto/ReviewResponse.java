package com.myfoodie.reviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private long id;
    private String userId;
    private long dishId;
    private double rating;
    private String title;
    private String comment;
    private LocalDate dateCreated;
    private String username;
}
