package com.mlorenzo.springbootdemo.rest.services;

import com.mlorenzo.springbootdemo.rest.dao.EmployeeDAO;
import com.mlorenzo.springbootdemo.rest.entities.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id).orElseThrow();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public Employee update(int id, Employee employee) {
        Employee foundEmployee = findById(id);
        BeanUtils.copyProperties(employee, foundEmployee, "id");
        return employeeDAO.save(foundEmployee);
    }

    @Override
    public void deleteById(int id) {
        Employee employee = findById(id);
        employeeDAO.delete(employee);
    }
}
