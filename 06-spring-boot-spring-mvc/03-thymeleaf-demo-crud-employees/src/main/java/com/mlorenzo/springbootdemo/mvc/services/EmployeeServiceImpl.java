package com.mlorenzo.springbootdemo.mvc.services;

import com.mlorenzo.springbootdemo.mvc.entities.Employee;
import com.mlorenzo.springbootdemo.mvc.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(int id, Employee employee) {
        Employee foundEmployee = findById(id);
        BeanUtils.copyProperties(employee, foundEmployee, "id");
        return employeeRepository.save(foundEmployee);
    }

    @Override
    public void deleteById(int id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);
    }
}
