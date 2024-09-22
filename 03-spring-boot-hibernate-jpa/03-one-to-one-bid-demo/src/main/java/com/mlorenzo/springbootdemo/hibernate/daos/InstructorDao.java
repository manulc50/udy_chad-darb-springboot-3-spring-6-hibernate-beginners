package com.mlorenzo.springbootdemo.hibernate.daos;

import com.mlorenzo.springbootdemo.hibernate.entities.Instructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface InstructorDao {

    @Transactional(readOnly = true)
    Optional<Instructor> findById(int id);

    @Transactional
    void save(Instructor instructor);

    @Transactional
    void deleteById(int id);
}
