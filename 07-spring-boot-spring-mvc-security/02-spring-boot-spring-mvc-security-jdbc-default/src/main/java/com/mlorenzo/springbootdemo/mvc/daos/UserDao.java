package com.mlorenzo.springbootdemo.mvc.daos;

import com.mlorenzo.springbootdemo.mvc.models.User;

public interface UserDao {
    Boolean existsByUsername(String username);
    void save(User user);
}
