package com.myfoodie.categoryservice.service;

import com.myfoodie.categoryservice.dto.CategoryDishResponse;
import com.myfoodie.categoryservice.dto.CategoryResponse;
import com.myfoodie.categoryservice.feign.DishServiceClient;
import com.myfoodie.categoryservice.model.Category;
import com.myfoodie.categoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final DishServiceClient dishServiceClient;


    @Override
    public List<CategoryResponse> getAllCategories() {
        var category = categoryRepository.findAll();
        return category.stream()
                .map(this::mapToCategoryResponse)
                .toList();
    }

    @Override
    public List<CategoryDishResponse> getCategoriesById(long id) {
        var categoriesById = categoryRepository.findAllById(id);
        return categoriesById.stream()
                .flatMap(category ->
                        category.getDishId().stream().map(dishId -> {
                            var dish = dishServiceClient.getDishesById(dishId);
                            return CategoryDishResponse.builder()
                                    .id(category.getId())
                                    .title(category.getTitle())
                                    .dishId(Collections.singletonList(dishId))
                                    .name(dish.getName())
                                    .description(dish.getDescription())
                                    .ingredients(dish.getIngredients())
                                    .recipe(dish.getRecipe())
                                    .imageURL(dish.getImageURL())
                                    .timeEstimate(dish.getTimeEstimate())
                                    .nutritionalContent(dish.getNutritionalContent())
                                    .mealType(dish.getMealType())
                                    .build();
                        })
                )
                .collect(Collectors.toList());
    }



    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .dishId(category.getDishId())
                .build();
    }


}
