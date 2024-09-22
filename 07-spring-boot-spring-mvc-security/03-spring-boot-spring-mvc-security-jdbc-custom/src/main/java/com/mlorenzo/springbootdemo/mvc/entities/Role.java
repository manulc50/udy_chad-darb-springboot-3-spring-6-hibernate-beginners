package com.mlorenzo.springbootdemo.mvc.entities;

import org.springframework.data.relational.core.mapping.Table;

@Table(name = "roles")
public class Role {
    // Nota: Faltaría indicar la clave primaria que, en nuestro caso, es una clave compuesta, pero parece ser que
    // en Spring Data Jdbc no es posible indicar una clave primaria compuesta. Aún así, la aplicación funciona
    // correctamente.
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
