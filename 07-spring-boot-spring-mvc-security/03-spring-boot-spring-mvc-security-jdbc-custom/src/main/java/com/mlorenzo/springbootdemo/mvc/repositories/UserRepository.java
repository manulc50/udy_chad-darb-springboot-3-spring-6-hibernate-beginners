package com.mlorenzo.springbootdemo.mvc.repositories;

import com.mlorenzo.springbootdemo.mvc.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    boolean existsByUserId(String userId);
}
