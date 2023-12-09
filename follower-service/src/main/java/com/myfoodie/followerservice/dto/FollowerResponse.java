package com.myfoodie.followerservice.dto;

import com.myfoodie.followerservice.model.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowerResponse {
    private String followerId;
    private String userId;
    private LocalDateTime followedDate;
    private String username;
    private List<FavoriteDishResponse> favoriteDishResponses;

}
