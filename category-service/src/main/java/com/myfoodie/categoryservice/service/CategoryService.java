package com.myfoodie.categoryservice.service;

import com.myfoodie.categoryservice.dto.CategoryDishResponse;
import com.myfoodie.categoryservice.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {


    List<CategoryResponse>getAllCategories();
    List<CategoryDishResponse>getCategoriesById(long id);

}
