package com.mlorenzo.springbootdemo.hibernate.daos;

import com.mlorenzo.springbootdemo.hibernate.entities.InstructorDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface InstructorDetailsDao {

    @Transactional(readOnly = true)
    Optional<InstructorDetail> findInstructorDetailById(int id);

    @Transactional
    void deleteInstructorDetailById(int id);
}
