package com.mlorenzo.springbootdemo.mvc.daos;

import com.mlorenzo.springbootdemo.mvc.entities.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RoleDao {

    @Transactional(readOnly = true)
    Optional<Role> findByName(String name);
}
