package com.mlorenzo.springbootdemo.mvc.daos;

import com.mlorenzo.springbootdemo.mvc.entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final EntityManager em;

    public RoleDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Role> findByName(String name) {
        final String sql = "from Role where name=:name";
        TypedQuery<Role> query = em.createQuery(sql, Role.class);
        query.setParameter("name", name);
        try {
            return Optional.of(query.getSingleResult());
        }
        catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
