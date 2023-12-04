package com.myfoodie.favoritedishservice.controller;

import com.myfoodie.favoritedishservice.dto.FavoriteDishRequest;
import com.myfoodie.favoritedishservice.dto.FavoriteDishResponse;
import com.myfoodie.favoritedishservice.service.FavoriteDishService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteDishController {


    private final FavoriteDishService favoriteDishService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDish(@RequestBody FavoriteDishRequest favoriteDishRequest) {
        favoriteDishService.addFavoriteDish(favoriteDishRequest);
    }


    @DeleteMapping("/{userId}/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDish(@PathVariable String userId,@PathVariable Long dishId) {
        favoriteDishService.removeFavoriteDish(userId,dishId);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteDishResponse> getFavoriteDishesByUserId(@PathVariable String id) {
        return favoriteDishService.getUserFavoriteDishes(id);
    }

    @GetMapping("/{id}/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteDishResponse> getFavoriteDishesByUserIdAndASpecificDate(
            @PathVariable String id,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return favoriteDishService.getFavoriteDishesAddedOnDate(id, date);
    }

    @GetMapping("/{id}/{StartDate}/{EndDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteDishResponse> getFavoriteDishesByUserIdAndBetweenToDates(
            @PathVariable String id,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate StartDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate EndDate
    ) {
        return favoriteDishService.getFavoriteDishesAddedBetweenDates(id, StartDate, EndDate);
    }

    @GetMapping("/dish/{userId}/{dishId}")
    @ResponseStatus(HttpStatus.OK)
    public FavoriteDishResponse getFavoriteDishByUserIdAndDishId(
            @PathVariable String userId,
            @PathVariable Long dishId
    ) {
        return favoriteDishService.getFavoriteDishByUserIdAndDishId(userId,dishId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllDishFromUser(@PathVariable String userId) {
        favoriteDishService.removeAllFavoriteDishesForUser(userId);
    }


}
