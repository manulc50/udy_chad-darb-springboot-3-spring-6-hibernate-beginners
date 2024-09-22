package com.mlorenzo.springbootdemo.rest.dao;

import com.mlorenzo.springbootdemo.rest.entities.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    @Transactional(readOnly = true)
    List<Employee> findAll();

    @Transactional(readOnly = true)
    Optional<Employee> findById(int id);

    @Transactional
    Employee save(Employee employee);

    @Transactional
    void delete(Employee employee);
}
