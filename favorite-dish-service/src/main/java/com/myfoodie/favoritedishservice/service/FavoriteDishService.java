package com.myfoodie.favoritedishservice.service;

import com.myfoodie.favoritedishservice.dto.FavoriteDishRequest;
import com.myfoodie.favoritedishservice.dto.FavoriteDishResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FavoriteDishService {

    void addFavoriteDish(FavoriteDishRequest favoriteDishRequest);
    void removeFavoriteDish(String userId, long dishId);

    List<FavoriteDishResponse> getUserFavoriteDishes(String userId);

    List<FavoriteDishResponse> getFavoriteDishesAddedOnDate(String userId, LocalDate date);

    List<FavoriteDishResponse> getFavoriteDishesAddedBetweenDates(String userId, LocalDate startDate, LocalDate endDate);

    FavoriteDishResponse getFavoriteDishByUserIdAndDishId(String userId, long dishId);
    void removeAllFavoriteDishesForUser(String userId);



}
