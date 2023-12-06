package com.myfoodie.dishservice.dto;

import com.myfoodie.dishservice.model.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {
    private long id;
    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> recipe;
    private String imageURL;
    private Integer timeEstimate;
    private List<String> nutritionalContent;
    private MealType mealType;
}
