package com.mlorenzo.springbootdemo.rest.repositories;

import com.mlorenzo.springbootdemo.rest.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
