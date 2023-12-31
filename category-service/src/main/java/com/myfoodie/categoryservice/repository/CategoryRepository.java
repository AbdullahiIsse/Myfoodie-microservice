package com.myfoodie.categoryservice.repository;

import com.myfoodie.categoryservice.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long> {


    List<Category>findAll();
    List<Category>findAllById(long id);
}
