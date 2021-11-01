package edu.deadshot.JpaSpecification.controller;

import edu.deadshot.JpaSpecification.entity.ComplexRequest;
import edu.deadshot.JpaSpecification.entity.Student;
import edu.deadshot.JpaSpecification.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/s")
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @GetMapping("/all")
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    @PostMapping("/complex")
    public List<Student> advancedSearch(@RequestBody ComplexRequest request) {
        return studentRepo.doComplexSearch(request);
    }

}
