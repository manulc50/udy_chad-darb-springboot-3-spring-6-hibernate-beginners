package com.mlorenzo.springbootdemo.mvc.springbootdemo.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class DemoController {

    @GetMapping({"/", "/hello"})
    public String sayHello(Model model) {
        model.addAttribute("theDate", new Date());
        return "helloworld";
    }

}
