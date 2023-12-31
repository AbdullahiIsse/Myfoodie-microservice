package com.myfoodie.dishservice.service;

import com.myfoodie.dishservice.dto.DishRequest;
import com.myfoodie.dishservice.dto.DishResponse;
import com.myfoodie.dishservice.model.Dish;
import jakarta.validation.Valid;

import java.util.List;

public interface DishService {

    void addDish(@Valid DishRequest dishRequest);

    void removeDishById(long id);

    void updateDishById(long id,@Valid DishRequest dishRequest);
    List<DishResponse> getAllDishes();

    List<DishResponse>getAllDishesFromType(String type);

    List<DishResponse>searchDishesByName(String name);

   List<DishResponse> getDishByIds(List<Long> dishId);

    DishResponse getDishById (long dishId);

    List<DishResponse>getTop10ByOrderByCreatedAtDesc();
}
