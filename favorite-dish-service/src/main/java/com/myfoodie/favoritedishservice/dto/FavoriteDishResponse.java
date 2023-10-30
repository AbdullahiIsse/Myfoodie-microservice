package com.myfoodie.favoritedishservice.dto;

import com.myfoodie.favoritedishservice.model.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
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
    private String recipe;
    private String imageURL;
    private Integer timeEstimate;
    private List<String> nutritionalContent;
    private MealType mealType;
}
