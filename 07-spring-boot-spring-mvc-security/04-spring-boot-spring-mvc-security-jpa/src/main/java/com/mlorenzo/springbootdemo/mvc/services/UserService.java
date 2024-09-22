package com.mlorenzo.springbootdemo.mvc.services;

import com.mlorenzo.springbootdemo.mvc.models.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean save(UserModel userModel);
}
