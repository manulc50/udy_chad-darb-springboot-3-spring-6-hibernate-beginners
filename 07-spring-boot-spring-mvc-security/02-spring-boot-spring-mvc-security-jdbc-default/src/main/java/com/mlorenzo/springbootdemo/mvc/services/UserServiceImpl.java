package com.mlorenzo.springbootdemo.mvc.services;

import com.mlorenzo.springbootdemo.mvc.daos.UserDao;
import com.mlorenzo.springbootdemo.mvc.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean save(User user) {
        if(userDao.existsByUsername(user.getUsername()))
            return false;
        user.setPassword("{bcrypt}" + bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }
}
