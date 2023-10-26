package com.myfoodie.dishservice.controller;

import com.myfoodie.dishservice.dto.DishRequest;
import com.myfoodie.dishservice.dto.DishResponse;
import com.myfoodie.dishservice.exception.DishNotFoundException;
import com.myfoodie.dishservice.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DishResponse> getAllDishes() {
        return dishService.getAllDishes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDish(@RequestBody DishRequest dishRequest) {
        dishService.addDish(dishRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDish(@PathVariable Long id, @RequestBody DishRequest dishRequest) throws DishNotFoundException {
        dishService.updateDishById(id, dishRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDish(@PathVariable Long id) {
        dishService.removeDishById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DishResponse getDish(@PathVariable Long id) throws DishNotFoundException {
        return dishService.getDishById(id);
    }

    @GetMapping("/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<DishResponse> getDishesByType(@PathVariable String type) {
        return dishService.getAllDishesFromType(type);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<DishResponse> searchDishesByName(@RequestParam String name) {
        return dishService.searchDishesByName(name);
    }


}
