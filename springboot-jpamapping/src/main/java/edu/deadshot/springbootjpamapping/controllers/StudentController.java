package edu.deadshot.springbootjpamapping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.deadshot.springbootjpamapping.models.Student;
import edu.deadshot.springbootjpamapping.repositories.StudentRepo;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepo studentRepo;
    
    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @PostMapping("/")
    public Student createStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }

}
