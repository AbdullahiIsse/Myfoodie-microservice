package com.myfoodie.reviewservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "review")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_generator")
    @SequenceGenerator(name = "review_generator", sequenceName = "review_seq", allocationSize = 1)
    private long id;

    @Column(name = "user_id")
    @NotBlank(message = "userId is required")
    private String userId;

    @Column(name = "dish_id")
    @NotNull(message = "dish_id is required")
    private long dishId;

    @Column(name = "rating")
    @NotNull(message = "rating is required")
    private double rating;

    @Column(name = "title")
    @NotBlank(message = "title is required")
    private String title;

    @Column(name = "comment")
    @NotBlank(message = "comment is required")
    private String comment;

    @Column(name = "date_created")
    @NotNull(message = "DateCreated is required")
    private LocalDate dateCreated;


}
