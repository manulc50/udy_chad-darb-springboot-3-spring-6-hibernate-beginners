package com.mlorenzo.springbootdemo.hibernate.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "instructors")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;

    // Nota: Si usamos el tipo de Cascade Merge o Persist y también activamos la opción orphanRemoval, no es necesario
    // usar también el tipo de Cascade REMOVE para eliminar también las entidades hijas de forma automática de la base
    // de datos porque, al eliminar un instructor, si tiene una entidad InstructorDetails asociada, ésta queda huérfana
    // y también se elimina. Sin embargo, si usamos solo el tipo de Cascade Merge o Persist sin la opción orphanRemoval,
    // entonces sí es necesario establecer también el tipo de Cascade REMOVE para eliminar las entidades hijas
    // automáticamente.
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    private InstructorDetail instructorDetail;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
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

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
