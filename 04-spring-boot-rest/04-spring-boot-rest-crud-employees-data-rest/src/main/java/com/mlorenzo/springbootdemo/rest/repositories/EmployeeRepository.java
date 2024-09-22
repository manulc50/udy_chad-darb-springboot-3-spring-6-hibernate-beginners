package com.mlorenzo.springbootdemo.rest.repositories;

import com.mlorenzo.springbootdemo.rest.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// Podemos usar esta anotación para cambiar el nombre por defecto de la ruta de los recursos que se expone.
// Por defecto, el nombre de la ruta que se expone es el nombre de la clase de la entidad en minúscula y en plural, es
// decir, en esta caso, el nombre de la ruta por defecto sería "employees".
//@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
