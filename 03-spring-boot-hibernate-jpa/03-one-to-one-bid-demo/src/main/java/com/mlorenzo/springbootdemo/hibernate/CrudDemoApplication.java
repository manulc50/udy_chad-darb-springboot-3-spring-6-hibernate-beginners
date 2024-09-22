package com.mlorenzo.springbootdemo.hibernate;

import com.mlorenzo.springbootdemo.hibernate.daos.InstructorDao;
import com.mlorenzo.springbootdemo.hibernate.daos.InstructorDetailsDao;
import com.mlorenzo.springbootdemo.hibernate.entities.Instructor;
import com.mlorenzo.springbootdemo.hibernate.entities.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class CrudDemoApplication implements CommandLineRunner {
	private final InstructorDao instructorDao;
	private final InstructorDetailsDao instructorDetailsDao;

	public CrudDemoApplication(InstructorDao instructorDao, InstructorDetailsDao instructorDetailsDao) {
		this.instructorDao = instructorDao;
		this.instructorDetailsDao = instructorDetailsDao;
	}


	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//createInstructor();
		//createInstructors();
		//findInstructor(3);
		//deleteInstructor(4);
		//findInstructorDetail(4);
		deleteInstructorDetail(4);
	}

	private void createInstructor() {
		Instructor instructor = new Instructor("Chad", "Darby", "char.darby@test.com");
		InstructorDetail instructorDetail = new InstructorDetail("http://test/youtube", "Football");
		instructor.setInstructorDetail(instructorDetail);
		System.out.println("Saving instructor...");
		instructorDao.save(instructor);
		System.out.println("Saved instructor: " + instructor);
		System.out.println("Saved instructor detail: " + instructor.getInstructorDetail());
	}

	private void createInstructors() {
		Instructor instructor1 = new Instructor("Madhu", "Patel", "madhu.patel@test.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("http://test/youtube", "Guitar");
		instructor1.setInstructorDetail(instructorDetail1);
		Instructor instructor2 = new Instructor("Chad", "Darby", "char.darby@test.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("http://test/youtube", "Football");
		instructor2.setInstructorDetail(instructorDetail2);
		System.out.println("Saving instructors...");
		instructorDao.save(instructor1);
		instructorDao.save(instructor2);
		System.out.println("Done!");
	}

	private void findInstructor(int id) {
		System.out.println("Finding instructor with id:" + id);
		instructorDao.findById(id)
				.ifPresentOrElse(instructor -> {
					System.out.println("Found instructor: " + instructor);
					System.out.println("Instructor detail: " + instructor.getInstructorDetail());
				},
				() -> System.out.println("Instructor not found"));
	}

	private void deleteInstructor(int id) {
		System.out.println("Deleting instructor with id:" + id);
		instructorDao.deleteById(id);
		System.out.println("Done!");
	}

	private void findInstructorDetail(int id) {
		Optional<InstructorDetail> oInstructorDetail = instructorDetailsDao.findInstructorDetailById(id);
		if(oInstructorDetail.isEmpty())
			System.out.println("Instructor detail not found");
		else {
			InstructorDetail instructorDetail = oInstructorDetail.get();
			System.out.println("Found instructor detail: " + instructorDetail);
			System.out.println("Instructor: " + instructorDetail.getInstructor());
		}
	}

	private void deleteInstructorDetail(int id) {
		System.out.println("Deleting instructor detail with id: " + id);
		instructorDetailsDao.deleteInstructorDetailById(id);
		System.out.println("Done!");
	}
}
