package com.mlorenzo.springbootdemo.mvc.controllers;

import com.mlorenzo.springbootdemo.mvc.models.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    // Esta anotación hace que este método se ejecute por cada petición http que llega a este controlador.
    // Registramos el editor "StringTrimmerEditor" para eliminar los espacios que puedan ser introducidos en los campos
    // de texto del formulario.
    // Nota: Otra alternativa es usar la anotación de validación @NotBlank
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Si pasamos true a este constructor y el editor detecta que hay espacios en blanco, devuelve null. En caso
        // contrario, devuelve un texto vacío "".
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/process-form")
    public String processForm(@Valid Customer customer, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "customer-form";
        return "customer-confirmation";
    }
}
