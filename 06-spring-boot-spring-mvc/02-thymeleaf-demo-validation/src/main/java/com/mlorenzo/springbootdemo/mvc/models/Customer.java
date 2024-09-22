package com.mlorenzo.springbootdemo.mvc.models;

import com.mlorenzo.springbootdemo.mvc.annotations.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {
    private String firstName;

    // Se comenta porque ahora manejamos esta validación usando un método anotado con @InitBinder en el controlador.
    //@NotBlank(message = "Is required")
    @Size(min = 3, message = "Must have at least 3 character")
    private String lastName;

    // 5 caracteres que pueden ser letrar minúsculas, letras mayúsculas o números enteros positivos
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Only 5 chars/digits")
    private String postalCode;

    @NotNull(message = "Is required")
    @Min(value = 1, message = "Must be greater than or equal to 1")
    @Max(value = 10, message = "Must be less than or equal to 10")
    private Integer freePasses;

    // Anotación personalizada. Por defecto value = "LUV" y message = "Must start with LUV"
    @CourseCode(value = "TOPS", message = "Must start with TOPS")
    private String courseCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
