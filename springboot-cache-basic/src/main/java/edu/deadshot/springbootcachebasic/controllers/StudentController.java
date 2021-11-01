package edu.deadshot.springbootcachebasic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.deadshot.springbootcachebasic.models.Student;
import edu.deadshot.springbootcachebasic.repositories.StudentRepo;
import edu.deadshot.springbootcachebasic.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentByIdService(id);
    }

}
