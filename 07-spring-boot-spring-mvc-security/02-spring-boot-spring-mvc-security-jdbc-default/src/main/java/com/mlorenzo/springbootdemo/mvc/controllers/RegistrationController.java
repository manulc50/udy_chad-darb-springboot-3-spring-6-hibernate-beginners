package com.mlorenzo.springbootdemo.mvc.controllers;

import com.mlorenzo.springbootdemo.mvc.models.User;
import com.mlorenzo.springbootdemo.mvc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show-my-registration-page")
    public String showMyRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/process-registration")
    public String processRegistration(@Validated User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors())
            return "registration";
        if(!userService.save(user)) {
            model.addAttribute("registrationError", "Username already exists.");
            return "registration";
        }
        return "confirmation-registration";
    }

}
