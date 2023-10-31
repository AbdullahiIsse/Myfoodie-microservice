package com.myfoodie.reviewservice.repository;

import com.myfoodie.reviewservice.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review,Long> {
}
