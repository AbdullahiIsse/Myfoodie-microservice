package com.myfoodie.categoryservice.controller;

import com.myfoodie.categoryservice.dto.CategoryDishResponse;
import com.myfoodie.categoryservice.dto.CategoryResponse;
import com.myfoodie.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDishResponse> getCategoriesById(@PathVariable long id) {
        return categoryService.getCategoriesById(id);
    }


}
