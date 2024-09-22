package com.mlorenzo.springbootdemo.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // En este caso, no utilizamos el esquema de tablas por defecto de Spring Security. Por lo tanto, tenemos que
    // indicar las consultas SQL para obtener los usuarios y sus roles.
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // El parámetro que se le pasa a ámbas consulas se corresponde con el username introducido por el usuario para
        // el login.
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select * from roles where user_id=?");
        // Si tuvieramos una base de datos con un esquema de tablas correspondiente a una relación muchos a muchos entre
        // una tabla "users" y otra tabla "roles", éstas serían las consultas SQL:
        /*jdbcUserDetailsManager.setUsersByUsernameQuery("select username,password,enabled from users where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select u.username,r.name from users as u inner join users_roles as ur " +
                "on u.id=ur.user_id inner join roles as r on ur.role_id=r.id where u.username=?");*/
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/show-my-login-page").permitAll()
                        .requestMatchers( "/registration/*").permitAll()
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders").hasRole("MANAGER")
                        .requestMatchers("/systems").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(loginConfigurer -> loginConfigurer
                        .loginPage("/show-my-login-page")
                        // Por defecto es "/login"
                        .loginProcessingUrl("/authenticate"))
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/deny-access"));
        return http.build();
    }
}
