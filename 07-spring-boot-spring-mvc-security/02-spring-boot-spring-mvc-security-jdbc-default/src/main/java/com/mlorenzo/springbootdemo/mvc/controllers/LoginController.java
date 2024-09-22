package com.mlorenzo.springbootdemo.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/show-my-login-page")
    public String showMyLoginPage() {
        return "fancy-login";
    }

    @GetMapping("/deny-access")
    public String showAccessDenied() {
        return "access-denied";
    }
}
