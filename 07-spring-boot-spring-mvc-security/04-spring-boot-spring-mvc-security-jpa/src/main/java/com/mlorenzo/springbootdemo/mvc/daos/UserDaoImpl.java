package com.mlorenzo.springbootdemo.mvc.daos;

import com.mlorenzo.springbootdemo.mvc.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager em;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        final String sql = "from User where username=:username";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        query.setParameter("username", username);
        try {
            return Optional.of(query.getSingleResult());
        }
        catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean existsByUsername(String username) {
        final String sql = "select case when count(u)=1 then true else false end from User u where u.username=?1";
        TypedQuery<Boolean> query = em.createQuery(sql, Boolean.class);
        query.setParameter(1, username);
        return query.getSingleResult();
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }
}
