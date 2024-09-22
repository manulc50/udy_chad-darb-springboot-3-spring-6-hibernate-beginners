package com.mlorenzo.springbootdemo.mvc.daos;

import com.mlorenzo.springbootdemo.mvc.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean existsByUsername(String username) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM Users WHERE username=? LIMIT 1)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, username);
    }

    @Override
    public void save(User user) {
        final String sql1 = "INSERT INTO users VALUES(?,?,?,?,?,?)";
        final String sql2 = "INSERT INTO authorities VALUES(?,'ROLE_EMPLOYEE')";
        jdbcTemplate.update(sql1, user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword(), true);
        jdbcTemplate.update(sql2, user.getUsername());
    }
}
