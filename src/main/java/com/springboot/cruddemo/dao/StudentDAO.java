package com.springboot.cruddemo.dao;

import java.util.List;

import com.springboot.cruddemo.entity.Student;

public interface StudentDAO {

	public void save(Student student);
	
	public Student fingByID(int id);
	
	public List<Student> findAll();
	
	public void update(Student thStudent);
	
	public void delete(int id);
	
	public int deleteAll();
}
