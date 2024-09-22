package com.mlorenzo.springbootdemo.mvc.services;

import com.mlorenzo.springbootdemo.mvc.entities.Role;
import com.mlorenzo.springbootdemo.mvc.entities.User;
import com.mlorenzo.springbootdemo.mvc.models.UserModel;
import com.mlorenzo.springbootdemo.mvc.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean save(UserModel userModel) {
        if(userRepository.existsByUserId(userModel.getUsername()))
            return false;
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        user.setUserId(userModel.getUsername());
        user.setPw("{bcrypt}" + bCryptPasswordEncoder.encode(userModel.getPassword()));
        user.setActive(true);
        user.setRoles(Set.of(new Role("ROLE_EMPLOYEE")));
        userRepository.save(user);
        return true;
    }
}
