package com.mlorenzo.springbootdemo.mvc.services;

import com.mlorenzo.springbootdemo.mvc.models.UserModel;

public interface UserService {
    boolean save(UserModel userModel);
}
