package com.myfoodie.categoryservice.dto;

import com.myfoodie.categoryservice.model.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {
    private String name;
    private String description;
    private List<String> ingredients;
    private String recipe;
    private String imageURL;
    private Integer timeEstimate;
    private List<String> nutritionalContent;
    private MealType mealType;
}
