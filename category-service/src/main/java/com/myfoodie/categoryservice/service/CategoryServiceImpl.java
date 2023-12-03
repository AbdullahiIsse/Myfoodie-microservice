package com.myfoodie.categoryservice.service;

import com.myfoodie.categoryservice.dto.CategoryDishResponse;
import com.myfoodie.categoryservice.dto.CategoryResponse;
import com.myfoodie.categoryservice.feign.DishServiceClient;
import com.myfoodie.categoryservice.model.Category;
import com.myfoodie.categoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return categoriesById
                .stream()
                .map(category ->
                        {
                            var dish = dishServiceClient.getDishesById(category.getDishId());
                            return CategoryDishResponse.builder()
                                    .id(category.getId())
                                    .title(category.getTitle())
                                    .dishId(category.getDishId())
                                    .name(dish.getName())
                                    .description(dish.getDescription())
                                    .ingredients(dish.getIngredients())
                                    .recipe(dish.getRecipe())
                                    .imageURL(dish.getImageURL())
                                    .timeEstimate(dish.getTimeEstimate())
                                    .nutritionalContent(dish.getNutritionalContent())
                                    .mealType(dish.getMealType())
                                    .build();
                        }
                )
                .toList();
    }


    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .dishId(category.getDishId())
                .build();
    }


}
