package com.mlorenzo.springbootdemo.mvc.services;

import com.mlorenzo.springbootdemo.mvc.daos.RoleDao;
import com.mlorenzo.springbootdemo.mvc.daos.UserDao;
import com.mlorenzo.springbootdemo.mvc.entities.Role;
import com.mlorenzo.springbootdemo.mvc.entities.User;
import com.mlorenzo.springbootdemo.mvc.models.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + "not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true,
                mapRolesToAuthorities(user.getRoles()));
    }

    @Override
    public boolean save(UserModel userModel) {
        if(userDao.existsByUsername(userModel.getUsername()))
            return false;
        User user = new User();
        BeanUtils.copyProperties(userModel, user, "password");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setEnabled(true);
        user.setRoles(Set.of(roleDao.findByName("ROLE_EMPLOYEE").orElseThrow()));
        userDao.save(user);
        return true;
    }

    private Set<SimpleGrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }
}
