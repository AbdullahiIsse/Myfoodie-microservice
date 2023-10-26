package com.myfoodie.dishservice.dto;

import com.myfoodie.dishservice.model.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {
    private String name;
    private String description;
    private String recipe;
    private String imageURL;
    private Integer timeEstimate;
    private String nutritionalContent;
    private MealType mealType;
}
