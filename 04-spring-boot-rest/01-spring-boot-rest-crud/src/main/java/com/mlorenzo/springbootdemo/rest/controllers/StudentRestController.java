package com.mlorenzo.springbootdemo.rest.controllers;

import com.mlorenzo.springbootdemo.rest.exceptions.NotFoundException;
import com.mlorenzo.springbootdemo.rest.models.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {
    private List<Student> students;

    @PostConstruct
    private void loadData() {
        students = Arrays.asList(new Student("Poornima", "Patel"), new Student("Mario", "Rossi"),
                new Student("Mary", "Smith"));
    }

    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if(studentId >= students.size() || studentId < 0)
            throw new NotFoundException("Student id not found - " + studentId);
        return students.get(studentId);
    }

    // Se comenta porque, ahora, estas excepciones se manejan de forma global en el controlador "ExceptionHandlerController"
    // en vez de manejarlas únicamente para este controlador
    /*@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                System.currentTimeMillis());
        return ResponseEntity.badRequest().body(errorResponse);
    }*/
}
