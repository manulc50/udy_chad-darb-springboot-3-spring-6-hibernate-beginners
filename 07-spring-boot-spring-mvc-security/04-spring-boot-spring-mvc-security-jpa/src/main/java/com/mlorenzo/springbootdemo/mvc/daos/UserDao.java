package com.mlorenzo.springbootdemo.mvc.daos;

import com.mlorenzo.springbootdemo.mvc.entities.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserDao {

    @Transactional(readOnly = true)
    Optional<User> findByUsername(String username);

    @Transactional(readOnly = true)
    Boolean existsByUsername(String username);

    @Transactional
    void save(User user);
}
