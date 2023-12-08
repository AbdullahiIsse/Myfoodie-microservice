package com.myfoodie.dishservice.repository;

import com.myfoodie.dishservice.model.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishRepository extends CrudRepository<Dish,Long> {

    @Override
    List<Dish>findAll();

    List<Dish> findDishByIdIn(List<Long> id);

    Dish findDishById(long id);

    List<Dish>findTop10ByOrderByCreatedAtDesc();
}
