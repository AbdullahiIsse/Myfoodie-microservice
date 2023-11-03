package com.myfoodie.calendarservice.dto;

import com.myfoodie.calendarservice.model.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponse {

    private String userId;
    private long dishId;
    private LocalDateTime date;
    private String name;
    private String description;
    private List<String> ingredients;
    private String recipe;
    private String imageURL;
    private Integer timeEstimate;
    private List<String> nutritionalContent;
    private MealType mealType;
}
