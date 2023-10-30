package com.myfoodie.favoritedishservice.repository;

import com.myfoodie.favoritedishservice.model.FavoriteDish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FavoriteDishRepository extends CrudRepository<FavoriteDish,Long> {

    @Override
    List<FavoriteDish>findAll();

    void removeFavoriteDishByUserIdAndDishId(String userId, long dishId);
    @Query("SELECT fd.dishId FROM favorite_dish fd WHERE fd.userId = :userId")
    List<Long> findDishIdsByUserId(@Param("userId") String userId);

    FavoriteDish findByUserIdAndDishId(String userId, long dishId);

    void removeAllByUserId(String userId);

}
