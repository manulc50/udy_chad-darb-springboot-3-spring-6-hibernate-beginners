package com.mlorenzo.springbootdemo.rest.controllers;

import com.mlorenzo.springbootdemo.rest.entities.Employee;
import com.mlorenzo.springbootdemo.rest.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List <Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        employeeService.deleteById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<Map<String, String>> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(Map.of("message", "Employee not found"), HttpStatus.NOT_FOUND);
    }
}
