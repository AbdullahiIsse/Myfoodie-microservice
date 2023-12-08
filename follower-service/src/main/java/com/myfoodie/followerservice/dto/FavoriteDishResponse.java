package com.myfoodie.followerservice.dto;

import com.myfoodie.followerservice.model.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDishResponse {
    private String userId;
    private long dishId;
    private LocalDate dateAdded;
    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> recipe;
    private String imageURL;
    private Integer timeEstimate;
    private List<String> nutritionalContent;
    private MealType mealType;
}
