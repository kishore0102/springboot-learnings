package com.fresco.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.fresco.springboot.model.Student;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	List<Student> students = new ArrayList<>();

	// @PostConstruct
	// public void init() {
	// Student s1 = new Student(15, "test", "java", 10.0);
	// students.add(s1);
	// System.out.println("students created");
	// }

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public List<Student> getAllStudents() {
		return students;
	}

	@RequestMapping(value = "/student/{rollno}", method = RequestMethod.GET)
	public Student getStudentByRollno(@PathVariable int rollno) {
		Student findStudent = new Student();
		for (Student s : students) {
			if (s.getRollNo() == rollno) {
				findStudent = s;
			}
		}
		return findStudent;
	}

	@PostMapping("/student")
	public ResponseEntity<?> createStudent(@RequestBody Student newStudent) {
		try {
			students.add(newStudent);
		} catch (Exception e) {
			return new ResponseEntity<String>("BAD_REQUEST", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("CREATED", HttpStatus.CREATED);
	}

	@PutMapping("/student/{rollno}")
	public void updateStudent(@PathVariable int rollno, @RequestBody Student newStudent) {
		for (Student s : students) {
			if (s.getRollNo() == rollno) {
				s.setAttendance(newStudent.getAttendance());
				s.setDepartment(newStudent.getDepartment());
				s.setRollNo(newStudent.getRollNo());
				s.setName(newStudent.getName());
				break;
			}
		}
	}

	@RequestMapping(value = "/student/{rollno}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStudent(@PathVariable int rollno) {
		Student deleteStudent = null;
		for (Student s : students) {
			if (s.getRollNo() == rollno) {
				deleteStudent = s;
			}
		}
		students.remove(deleteStudent);
		return ResponseEntity.ok("deleted");
	}

}
