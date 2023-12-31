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
public class CategoryDishResponse {
    private long id;
    private String title;
    private List<Long> dishId;
    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> recipe;
    private String imageURL;
    private Integer timeEstimate;
    private List<String> nutritionalContent;
    private MealType mealType;
}
