package com.mlorenzo.springbootdemo.hibernate.daos;

import com.mlorenzo.springbootdemo.hibernate.entities.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    @Transactional(readOnly = true)
    List<Student> findAll();

    @Transactional(readOnly = true)
    Optional<Student> findById(int id);

    @Transactional(readOnly = true)
    List<Student> findByLastName(String lastName);

    @Transactional
    void save(Student student);

    @Transactional
    void update(Student student);

    @Transactional
    void deleteById(int id);

    @Transactional
    int deleteAll();
}
