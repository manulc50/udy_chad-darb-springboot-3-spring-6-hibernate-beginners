package com.mlorenzo.springbootdemo.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // Nota: Usando este manejador con la configuración por defecto, tenemos que utilizar en la base de datos
    // el esquema de tablas por defecto de Spring Security(ver los scripts del directorio "sql-scripts").
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET,"/show-my-login-page").permitAll()
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
