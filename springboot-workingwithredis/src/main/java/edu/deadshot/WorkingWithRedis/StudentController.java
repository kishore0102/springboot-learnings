package edu.deadshot.WorkingWithRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/student/{rollno}")
    public ResponseEntity<Object> getStudentByRollNo(@PathVariable(value = "rollno") int rollno) {
        return new ResponseEntity<>(studentService.getStudentByRollno(rollno), HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<Object> createStudent(@RequestBody Student newStudent) {
        studentService.addStudent(newStudent);
        return new ResponseEntity<>("student added successfully", HttpStatus.OK);
    }

}