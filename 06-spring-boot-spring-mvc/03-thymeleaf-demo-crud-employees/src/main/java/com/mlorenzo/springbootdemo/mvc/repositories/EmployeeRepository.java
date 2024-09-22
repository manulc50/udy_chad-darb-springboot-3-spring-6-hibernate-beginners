package com.mlorenzo.springbootdemo.mvc.repositories;

import com.mlorenzo.springbootdemo.mvc.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByOrderByLastNameAsc();
}
