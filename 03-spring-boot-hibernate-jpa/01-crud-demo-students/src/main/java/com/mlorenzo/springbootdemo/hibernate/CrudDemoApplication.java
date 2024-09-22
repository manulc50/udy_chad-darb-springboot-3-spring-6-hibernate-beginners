package com.mlorenzo.springbootdemo.hibernate;

import com.mlorenzo.springbootdemo.hibernate.daos.StudentDao;
import com.mlorenzo.springbootdemo.hibernate.entities.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	// Nota: A priori, la anotación @Transactional no funciona en métodos anotados con @Bean y tampoco en métodos
	// internos, es decir, métodos llamados desde otros métodos.
	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			createMultipleStudent(studentDao);
			//createStudent(studentDao);
			//readStudent(studentDao, 2);
			//readAllStudents(studentDao);
			//readStudentsByLastName(studentDao, "Doe");
			//updateStudent(studentDao, 2);
			//deleteStudent(studentDao, 4);
			//deleteAllStudents(studentDao);
		};
	}

	private void createStudent(StudentDao studentDao) {
		System.out.println("Creating new student...");
		Student student = new Student("Paul", "Doe", "paul.doe@test.com");
		System.out.println("Saving the student...");
		studentDao.save(student);
		System.out.println("Saved student. Generated id: " + student.getId());
	}

	private void createMultipleStudent(StudentDao studentDao) {
		System.out.println("Creating 3 student...");
		Student student1 = new Student("John", "Doe", "john.doe@test.com");
		Student student2 = new Student("Mary", "Public", "mary.public@test.com");
		Student student3 = new Student("Bonita", "Applebum", "bonita.applebum@test.com");
		System.out.println("Saving the students...");
		studentDao.save(student1);
		studentDao.save(student2);
		studentDao.save(student3);
	}

	private void readStudent(StudentDao studentDao, int studentId) {
		System.out.println("Retrieving student with id: " + studentId);
		studentDao.findById(studentId)
				.ifPresentOrElse(student -> System.out.println("Found the student: " + studentId),
						() -> System.out.println("Student with id " + studentId + " not found"));
	}

	private void readAllStudents(StudentDao studentDao) {
		// Versión simplificada de la expresión "student -> System.out.println(student)"
		studentDao.findAll().forEach(System.out::println);
	}

	private void readStudentsByLastName(StudentDao studentDao, String lastName) {
		List<Student> students = studentDao.findByLastName(lastName);
		for(Student student: students)
			System.out.println(student);
	}

	// Nota: Como no creamos una transacción a este nivel mediante la anotación @Transactional, después de invocarse el
	// método "findById", la entidad "Student" queda fuera del contexto de persistencia(estado "detached"), pero, luego,
	// el método "merge", que utiliza nuestro método "update", volverá a meter dicha entidad en el contexto de persistencia
	// mediante una consulta a la base de datos para realizar la actualización a continuación.
	// Sin embargo, si creamos una transacción a este nivel mediante la anotación @Transactional, después de invocarse el
	//método "findById", la entidad "Student" se mantendrá dentro del contexto de persistencia y no sería necesaria la
	// llamada a nuestro método "update" porque, al estar la entidad en el contexto de persistencia, su actualización se
	// llevará a cabo cuando finalice la transacción.
	public void updateStudent(StudentDao studentDao, int studentId) {
		System.out.println("Getting student by id: " + studentId);
		studentDao.findById(studentId).ifPresentOrElse(student -> {
					System.out.println("Updating student...");
					student.setFirstName("Scooby");
					studentDao.update(student);
					System.out.println("Updated student: " + student);
				},
				() -> System.out.println("Student with id " + studentId + " not found"));
	}

	private void deleteStudent(StudentDao studentDao, int studentId) {
		System.out.println("Deleting student by id: " + studentId);
		studentDao.deleteById(studentId);
	}

	private void deleteAllStudents(StudentDao studentDao) {
		System.out.println("Deleting all students...");
		System.out.println("Number of deleted students: " + studentDao.deleteAll());
	}
}
