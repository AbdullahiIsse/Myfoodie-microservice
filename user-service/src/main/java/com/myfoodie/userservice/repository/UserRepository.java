package com.myfoodie.userservice.repository;

import com.myfoodie.userservice.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    List<User> findAll();
    User findUserById(String id);

    @Query("SELECT u FROM user_account u WHERE u.id != ?1")
    List<User> getAllOtherUsers(String userId);

    boolean existsUserById(String id);

    boolean existsUserByEmail (String email);
}
