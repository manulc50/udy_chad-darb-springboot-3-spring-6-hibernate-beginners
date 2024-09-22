package com.mlorenzo.springbootdemo.hibernate.daos;

import com.mlorenzo.springbootdemo.hibernate.entities.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InstructorDaoImpl implements InstructorDao {
    private final EntityManager em;

    public InstructorDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Instructor> findById(int id) {
        return Optional.ofNullable(em.find(Instructor.class, id));
    }

    @Override
    public Instructor save(Instructor instructor) {
        return em.merge(instructor);
    }

    @Override
    public void deleteById(int id) {
        em.remove(findById(id).orElseThrow());

    }


}
