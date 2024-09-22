package com.mlorenzo.springbootdemo.rest.dao;

import com.mlorenzo.springbootdemo.rest.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    private final EntityManager em;

    public EmployeeDAOJpaImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Employee> findAll() {
        // Create a query
        TypedQuery<Employee> query = em.createQuery("from Employee", Employee.class);
        // Execute the query and get result list
        return query.getResultList();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(em.find(Employee.class, id));
    }

    @Override
    public Employee save(Employee employee) {
        return em.merge(employee);
    }

    @Override
    public void delete(Employee employee) {
        em.remove(employee);
    }
}
