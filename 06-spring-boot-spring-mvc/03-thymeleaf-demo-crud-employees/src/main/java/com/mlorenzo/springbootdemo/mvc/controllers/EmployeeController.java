package com.mlorenzo.springbootdemo.mvc.controllers;

import com.mlorenzo.springbootdemo.mvc.entities.Employee;
import com.mlorenzo.springbootdemo.mvc.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listAll(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employee/list";
    }

    @GetMapping("/show-add-form")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/save-form";
    }

    @GetMapping("/{employeeId}/show-update-form")
    public String showAddForm(@PathVariable("employeeId") int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employee/save-form";
    }

    @PostMapping("/save")
    public String save(Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/{employeeId}/delete")
    public String delete(@PathVariable(value = "employeeId") int id) {
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
