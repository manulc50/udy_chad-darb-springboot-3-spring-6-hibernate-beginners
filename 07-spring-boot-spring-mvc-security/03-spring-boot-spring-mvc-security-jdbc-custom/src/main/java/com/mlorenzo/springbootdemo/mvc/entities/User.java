package com.mlorenzo.springbootdemo.mvc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table(name = "members")
public class User {

    // Realmente es el username
    @Id
    private String userId;

    private String pw;
    private boolean active;
    private String firstName;
    private String lastName;
    private String email;

    // Como en nuestro caso el id se establece de forma manual porque se trata del username que se establece desde el
    // formulario de registro, para que nuestro repositorio "UserRepository", que extiende de CrudRespository, pueda
    // saber cuando una entidad es nueva o no lo es, como no pude determinarse por el id, una alternativa es usar este
    // campo "version". De esta manera, el método "save" de "CrudRepository" entenderá que una entidad es nueva si el
    // valor de este campo es 1(o null si el tipo del campo fuera un objeto Wrapper).
    @Version
    private int version;

    @MappedCollection(idColumn = "user_id")
    private Set<Role> roles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
