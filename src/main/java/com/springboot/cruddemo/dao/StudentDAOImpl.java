package com.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentDAOImpl implements StudentDAO {

	//define entitiy manager
	private EntityManager entityManager;
	
	//inject entity manager using constructor injecction
	@Autowired
	public StudentDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void save(Student student) {
		entityManager.persist(student);
		
	}

	@Override
	public Student fingByID(int id) {
		return entityManager.find(Student.class, id);
	}

	@Override
	public List<Student> findAll() {
		//create query
		TypedQuery<Student> theQuery= entityManager.createQuery("FROM Student order by firstName", Student.class);
		
		//return query results
		return theQuery.getResultList();
	}

	@Override
	@Transactional
	public void update(Student theStudent) {
		
		entityManager.merge(theStudent);
	}

	@Override
	@Transactional
	public void delete(int id) {
		
		Student myStudent = entityManager.find(Student.class, id);

		entityManager.remove(myStudent);
	}

	@Override
	@Transactional
	public int deleteAll() {
		int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
		return numRowsDeleted;
	}

}
