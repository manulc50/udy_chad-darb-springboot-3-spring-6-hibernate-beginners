package com.mlorenzo.springbootdemo.rest.services;

import com.mlorenzo.springbootdemo.rest.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    Employee update(int id, Employee employee);
    void deleteById(int id);
}
