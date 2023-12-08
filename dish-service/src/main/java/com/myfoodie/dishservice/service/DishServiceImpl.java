package com.myfoodie.dishservice.service;

import com.myfoodie.dishservice.dto.DishRequest;
import com.myfoodie.dishservice.dto.DishResponse;
import com.myfoodie.dishservice.exception.DishNotFoundException;
import com.myfoodie.dishservice.model.Dish;
import com.myfoodie.dishservice.repository.DishRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DishServiceImpl implements DishService {


    private final DishRepository dishRepository;


    @Override
    @Transactional
    public void addDish(@Valid DishRequest dishRequest) {
        Dish dish = Dish.builder()
                .name(dishRequest.getName())
                .description(dishRequest.getDescription())
                .ingredients(dishRequest.getIngredients())
                .recipe(dishRequest.getRecipe())
                .imageURL(dishRequest.getImageURL())
                .timeEstimate(dishRequest.getTimeEstimate())
                .mealType(dishRequest.getMealType())
                .nutritionalContent(dishRequest.getNutritionalContent())
                .createdAt(dishRequest.getCreatedAt()).build();

        dishRepository.save(dish);

        log.info("Dish {} is saved", dish.getId());
    }

    @Override
    @Transactional
    public void removeDishById(long id) {
        dishRepository.deleteById(id);
        log.info("Dish {} is deleted", id);
    }

    @Override
    @Transactional
    public void updateDishById(long id, @Valid DishRequest dishRequest) {
        Dish dish = dishRepository.findDishById(id);
        if (dish != null) {
            if (dishRequest.getName() != null) {
                dish.setName(dishRequest.getName());
            }

            if (dishRequest.getDescription() != null) {
                dish.setDescription(dishRequest.getDescription());
            }

            if (dishRequest.getIngredients() != null) {
                dish.setIngredients(dishRequest.getIngredients());
            }

            if (dishRequest.getRecipe() != null) {
                dish.setRecipe(dishRequest.getRecipe());
            }

            if (dishRequest.getImageURL() != null) {
                dish.setRecipe(dishRequest.getRecipe());
            }

            if (dishRequest.getTimeEstimate() != null) {
                dish.setTimeEstimate(dishRequest.getTimeEstimate());
            }

            if (dishRequest.getMealType() != null) {
                dish.setMealType(dishRequest.getMealType());
            }

            if (dishRequest.getNutritionalContent() != null) {
                dish.setNutritionalContent(dishRequest.getNutritionalContent());
            }

            dishRepository.save(dish);

            log.info("Dish {} has been updated", dish.getId());
        } else {
            log.error("Dish with id {} not found", id);
            throw new DishNotFoundException("Dish not found with id " + id);
        }
    }

    @Override
    public List<DishResponse> getAllDishes() {
        List<Dish> dishList = dishRepository.findAll();
        return dishList.stream()
                .map(this::mapToDishResponse)
                .toList();
    }

    @Override
    public List<DishResponse> getAllDishesFromType(String type) {
        String lowerCaseType = type.toLowerCase();
        List<Dish> dishList = dishRepository.findAll();
        return dishList.stream()
                .map(this::mapToDishResponse)
                .filter(dishResponse -> dishResponse.getMealType().name().toLowerCase().equals(lowerCaseType))
                .toList();
    }

    @Override
    public List<DishResponse> searchDishesByName(String name) {
        String lowerCaseName = name.toLowerCase();
        List<Dish> dishList = dishRepository.findAll();
        return dishList.stream()
                .map(this::mapToDishResponse)
                .filter(dishResponse -> dishResponse.getName().toLowerCase().contains(lowerCaseName))
                .toList();
    }

    @Override
    public  List<DishResponse> getDishByIds(List<Long> dishId) {
       return dishRepository.findDishByIdIn(dishId).stream()
                .map(this::mapToDishResponse)
                .toList();
    }

    public DishResponse getDishById (long dishId){
        var dish = dishRepository.findDishById(dishId);
        return mapToDishResponse(dish);
    }

    @Override
    public List<DishResponse> getTop10ByOrderByCreatedAtDesc() {
        return dishRepository.findTop10ByOrderByCreatedAtDesc().stream()
                .map(this::mapToDishResponse)
                .toList();
    }

    private DishResponse mapToDishResponse(Dish dish) {
        return DishResponse.builder()
                .id(dish.getId())
                .name(dish.getName())
                .description(dish.getDescription())
                .ingredients(dish.getIngredients())
                .recipe(dish.getRecipe())
                .imageURL(dish.getImageURL()).timeEstimate(dish.getTimeEstimate())
                .mealType(dish.getMealType())
                .nutritionalContent(dish.getNutritionalContent()).build();

    }
}
