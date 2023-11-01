package com.myfoodie.followerservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "follower")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follower {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "follower_generator")
    @SequenceGenerator(name = "follower_generator", sequenceName = "follower_seq", allocationSize = 1)
    private long id;

    @Column(name = "follower_id")
    @NotBlank(message = "follower_id is required")
    private String followerId;

    @Column(name = "user_id")
    @NotBlank(message = "userId is required")
    private String userId;

    @Column(name = "followed_date", nullable = false)
    @NotNull(message = "followedDate is required")
    private LocalDateTime followedDate;




}
