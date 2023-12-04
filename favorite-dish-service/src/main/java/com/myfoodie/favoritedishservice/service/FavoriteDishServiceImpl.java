package com.myfoodie.favoritedishservice.service;

import com.myfoodie.favoritedishservice.dto.DishResponse;
import com.myfoodie.favoritedishservice.dto.FavoriteDishRequest;
import com.myfoodie.favoritedishservice.dto.FavoriteDishResponse;
import com.myfoodie.favoritedishservice.feign.DishServiceClient;
import com.myfoodie.favoritedishservice.model.FavoriteDish;
import com.myfoodie.favoritedishservice.repository.FavoriteDishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteDishServiceImpl implements FavoriteDishService {

    private final FavoriteDishRepository favoriteDishRepository;
    private final DishServiceClient dishServiceClient;

    @Override
    @Transactional
    public void addFavoriteDish(FavoriteDishRequest favoriteDishRequest) {
        FavoriteDish favoriteDish = FavoriteDish.builder()
                .userId(favoriteDishRequest.getUserId())
                .dishId(favoriteDishRequest.getDishId())
                .dateAdded(favoriteDishRequest.getDateAdded())
                .build();

        favoriteDishRepository.save(favoriteDish);
        log.info("FavoriteDish {} is saved", favoriteDish.getId());

    }

    @Override
    @Transactional
    public void removeFavoriteDish(String userId, long dishId) {
        favoriteDishRepository.removeFavoriteDishByUserIdAndDishId(userId, dishId);
        log.info("FavoriteDish from user {} is removed", userId);
    }

    @Override
    public List<FavoriteDishResponse> getUserFavoriteDishes(String userId) {
        return getFavoriteDishesByFilter(userId, (favoriteDishResponse, favoriteDish) -> true);
    }

    @Override
    public List<FavoriteDishResponse> getFavoriteDishesAddedOnDate(String userId, LocalDate date) {
        return getFavoriteDishesByFilter(userId, (favoriteDishResponse, favoriteDish) -> favoriteDishResponse.getDateAdded().isEqual(date));
    }

    @Override
    public List<FavoriteDishResponse> getFavoriteDishesAddedBetweenDates(String userId, LocalDate startDate, LocalDate endDate) {
        return getFavoriteDishesByFilter(userId, (favoriteDishResponse, favoriteDish) ->
                favoriteDishResponse.getDateAdded().isAfter(startDate) &&
                        favoriteDishResponse.getDateAdded().isBefore(endDate)
        );
    }

    @Override
    public FavoriteDishResponse getFavoriteDishByUserIdAndDishId(String userId, long dishId) {
        FavoriteDish favoriteDish = favoriteDishRepository.findByUserIdAndDishId(userId, dishId);

        if (favoriteDish == null) {
            log.warn("FavoriteDish with userId {} and dishId {} not found", userId, dishId);
            return null;
        }

        List<DishResponse> dishResponseList = dishServiceClient.getDishByIds(List.of(dishId));

        if (dishResponseList.isEmpty()) {
            log.warn("Dish with dishId {} not found", dishId);
            return null;
        }

        DishResponse dishResponse = dishResponseList.get(0);
        return mapToFavoriteDishResponse(dishId, favoriteDish, dishResponse);
    }

    @Override
    @Transactional
    public void removeAllFavoriteDishesForUser(String userId) {
        favoriteDishRepository.removeAllByUserId(userId);
        log.info("all the favorite Dish from user {} is removed", userId);
    }

    private FavoriteDishResponse mapToFavoriteDishResponse(Long dishId, FavoriteDish favoriteDish, DishResponse dishResponse) {
        return FavoriteDishResponse.builder()
                .userId(favoriteDish.getUserId())
                .dishId(dishId)
                .dateAdded(favoriteDish.getDateAdded())
                .name(dishResponse.getName())
                .description(dishResponse.getDescription())
                .ingredients(dishResponse.getIngredients())
                .recipe(dishResponse.getRecipe())
                .imageURL(dishResponse.getImageURL())
                .timeEstimate(dishResponse.getTimeEstimate())
                .nutritionalContent(dishResponse.getNutritionalContent())
                .mealType(dishResponse.getMealType())
                .build();
    }


    private List<FavoriteDishResponse> getFavoriteDishesByFilter(String userId, BiPredicate<FavoriteDishResponse, FavoriteDish> filter) {
        List<Long> dishIdsByUserId = favoriteDishRepository.findDishIdsByUserId(userId);
        List<DishResponse> dishResponseList = dishServiceClient.getDishByIds(dishIdsByUserId);

        return IntStream.range(0, dishIdsByUserId.size())
                .mapToObj(i -> {
                    Long dishId = dishIdsByUserId.get(i);
                    FavoriteDish favoriteDish = favoriteDishRepository.findByUserIdAndDishId(userId, dishId);
                    DishResponse dishResponse = dishResponseList.get(i);
                    return mapToFavoriteDishResponse(dishId, favoriteDish, dishResponse);
                })
                .filter(favoriteDishResponse -> filter.test(favoriteDishResponse, favoriteDishRepository.findByUserIdAndDishId(userId, favoriteDishResponse.getDishId())))
                .toList();
    }
}
