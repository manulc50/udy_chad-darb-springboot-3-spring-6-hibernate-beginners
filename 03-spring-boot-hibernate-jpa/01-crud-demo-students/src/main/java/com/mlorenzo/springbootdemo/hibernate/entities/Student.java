package com.mlorenzo.springbootdemo.hibernate.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // En este caso, el uso de esta anotación es opcional porque estamos usando el valor por defecto
    @Column(name = "id")
    private int id;

    // En este caso, el uso de esta anotación también es opcional
    @Column(name = "first_name")
    private String firstName;

    // En este caso, el uso de esta anotación también es opcional
    @Column(name = "last_name")
    private String lastName;

    // En este caso, el uso de esta anotación también es opcional
    @Column(name = "email")
    private String email;

    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
