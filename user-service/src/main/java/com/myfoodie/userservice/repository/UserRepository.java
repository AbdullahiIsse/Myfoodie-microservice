package com.myfoodie.userservice.repository;

import com.myfoodie.userservice.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    List<User> findAll();
}
