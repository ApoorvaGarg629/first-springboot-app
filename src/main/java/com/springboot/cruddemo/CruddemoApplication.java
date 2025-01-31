package com.springboot.cruddemo;

import java.util.List;

import org.hibernate.annotations.DialectOverride.SQLDeleteAll;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.cruddemo.dao.StudentDAO;
import com.springboot.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createStudent(studentDAO);
			//createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			deleteAll(studentDAO);
			
		};
	}

	private void deleteAll(StudentDAO studentDAO) {

		int rowsDeleted = studentDAO.deleteAll();
		System.out.println(rowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {

		Student myStudent = studentDAO.fingByID(3);
		
		studentDAO.delete(3);
		System.out.println(myStudent + "deleted ...");
		
	}

	private void updateStudent(StudentDAO studentDAO) {

		Student myStudent = studentDAO.fingByID(1);
		
		myStudent.setFirstName("Amy");
		studentDAO.update(myStudent);
		
		System.out.println(myStudent);
		
	}

	private void queryForStudents(StudentDAO studentDAO) {

		//get list of students
		List<Student> allStudents = studentDAO.findAll();
		
		//display the list of students
		for(Student theStudent: allStudents) {
			System.out.println(theStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		
		System.out.println("Creating new student ...");
		Student tempStudent = new Student("Jimmy", "Robert", "amydoe@gmail.com");
		
		//save student
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);
		
		//display the saved student by id: primary key
		Student myStudent = studentDAO.fingByID(tempStudent.getId());
		
		System.out.println(myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 new students ...");
		Student tempStudent1 = new Student("Paul", "Doe", "pauldoe@gmail.com");
		Student tempStudent2 = new Student("John", "Doe", "johndoe@gmail.com");
		Student tempStudent3 = new Student("Marie", "Doe", "mariedoe@gmail.com");


		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	
	}

	private void createStudent(StudentDAO studentDAO) {
		
		//create student
		System.out.println("Creating new student ...");
		Student tempStudent = new Student("Amy", "Doe", "amydoe@gmail.com");
		
		//save student
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);
		
		
		//display id of saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
