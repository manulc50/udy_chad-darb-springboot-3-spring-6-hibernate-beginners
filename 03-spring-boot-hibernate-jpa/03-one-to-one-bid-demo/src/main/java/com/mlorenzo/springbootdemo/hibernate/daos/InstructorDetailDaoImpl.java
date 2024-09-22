package com.mlorenzo.springbootdemo.hibernate.daos;

import com.mlorenzo.springbootdemo.hibernate.entities.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class InstructorDetailDaoImpl implements InstructorDetailsDao {
    private final EntityManager em;

    public InstructorDetailDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<InstructorDetail> findInstructorDetailById(int id) {
        return Optional.ofNullable(em.find(InstructorDetail.class, id));
    }

    @Override
    public void deleteInstructorDetailById(int id) {
        // Versión simplificada de la expresión "instructorDetail -> em.remove(instructorDetail)"
        findInstructorDetailById(id).ifPresentOrElse(instructorDetail -> {
            instructorDetail.getInstructor().setInstructorDetail(null);
            em.remove(instructorDetail);
            }, () -> { throw new NoSuchElementException("No value present"); }
        );
    }
}
