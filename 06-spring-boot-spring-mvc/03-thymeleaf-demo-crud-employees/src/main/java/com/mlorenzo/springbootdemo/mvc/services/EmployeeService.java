package com.mlorenzo.springbootdemo.mvc.services;

import com.mlorenzo.springbootdemo.mvc.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    Employee update(int id, Employee employee);
    void deleteById(int id);
}
