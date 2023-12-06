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
public class DishRequest {
    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> recipe;
    private String imageURL;
    private Integer timeEstimate;
    private MealType mealType;
    private List<String> nutritionalContent;
    private Date createdAt;
}
