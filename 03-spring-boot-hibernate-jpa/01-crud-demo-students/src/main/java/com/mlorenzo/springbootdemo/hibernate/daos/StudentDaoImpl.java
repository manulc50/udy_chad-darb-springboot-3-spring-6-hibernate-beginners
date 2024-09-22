package com.mlorenzo.springbootdemo.hibernate.daos;

import com.mlorenzo.springbootdemo.hibernate.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {
    private final EntityManager em;

    public StudentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Optional<Student> findById(int id) {
        return Optional.ofNullable(em.find(Student.class, id));
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = em.createQuery("FROM Student WHERE lastName=:lastName", Student.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public void save(Student student) {
        // Mete o Persiste una entidad en el contexto de persistencia para ser guardada posteriormente en la base de datos
        em.persist(student);
    }

    @Override
    public void update(Student student) {
        // Mete o Persiste una entidad en el contexto de persistencia. Si el id de esa entidad en nulo, será guardada
        // posteriormente en la base de datos. En caso contrario, se recupera de la base de datos la entidad antigua a
        // partir de ese id y se actualiza con los nuevos datos de esa entidad.
        em.merge(student);
    }

    @Override
    public void deleteById(int id) {
        // Nota: Este método elimina un registro de la base de datos a partir de una entidad que esté dentro del contexto
        // de persistencia. Si la entidad no está dentro del contexto de persistencia, el método lanzará una excepción.
        em.remove(findById(id).orElseThrow());
    }

    @Override
    public int deleteAll() {
        return em.createQuery("DELETE FROM Student").executeUpdate();
    }
}
