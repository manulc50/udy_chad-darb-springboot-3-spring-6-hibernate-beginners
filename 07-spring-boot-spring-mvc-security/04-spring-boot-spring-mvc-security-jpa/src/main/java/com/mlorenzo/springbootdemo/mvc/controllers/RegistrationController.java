package com.mlorenzo.springbootdemo.mvc.controllers;

import com.mlorenzo.springbootdemo.mvc.models.UserModel;
import com.mlorenzo.springbootdemo.mvc.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        model.addAttribute("user", new UserModel());
        return "registration";
    }

    // Nota: Si no usamos la anotación @ModelAttribute("user"), el atributo del usuario en la vista "registration"
    // tendrá el nombre "userModel", sin embargo, en esa vista hacemos referencia al nombre "user".
    @PostMapping("/process-registration")
    public String processRegistration(@Valid @ModelAttribute("user") UserModel user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors())
            return "registration";
        if(!userService.save(user)) {
            model.addAttribute("registrationError", "Username already exists.");
            return "registration";
        }
        return "confirmation-registration";
    }

}
