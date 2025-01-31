package com.springboot.cruddemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboot.cruddemo.entity.Student;
import com.springboot.cruddemo.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService theStudentService) {
    	studentService = theStudentService;
    }

    // expose "/students" and return a list of students
    @GetMapping("/students")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    // add mapping for GET /students/{studentId}

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

    	Student theStudent = studentService.findById(studentId);

        if (theStudent == null) {
            throw new RuntimeException("Student id not found - " + studentId);
        }

        return theStudent;
    }

    // add mapping for POST /students - add new student

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student theStudent) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theStudent.setId(0);

        Student dbStudent = studentService.save(theStudent);

        return dbStudent;
    }

    // add mapping for PUT /students - update existing student

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student theStudent) {

    	Student dbStudent = studentService.save(theStudent);

        return dbStudent;
    }

    // add mapping for DELETE /students/{studentId} - delete student

    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId) {

    	Student temp = studentService.findById(studentId);

        // throw exception if null

        if (temp == null) {
            throw new RuntimeException("Student id not found - " + studentId);
        }

        studentService.deleteById(studentId);

        return "Deleted student id - " + studentId;
    }

}
