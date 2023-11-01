package com.myfoodie.followerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowerRequest {

    private String followerId;
    private String userId;
    private LocalDateTime followedDate;

}
