package edu.deadshot.MapStruct.controller;

import edu.deadshot.MapStruct.mapper.StudentMapper;
import edu.deadshot.MapStruct.model.Student;
import edu.deadshot.MapStruct.model.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    List<Student> studentList = new ArrayList<>();

    @Autowired
    StudentMapper studentMapper;

    @PostConstruct
    public void init() {
        Student s1 = new Student(1, "name1", "jan 1 2000", "name1@mail", "111", "java", "tech lead");
        Student s2 = new Student(2, "name2", "jan 1 2000", "name2@mail", "111", "python", "tech lead");
        Student s3 = new Student(3, "name3", "jan 1 2000", "name3@mail", "111", "english", "non-tech lead");
        studentList.addAll(Arrays.asList(s1, s2, s3));
    }

    @GetMapping("/mapstruct")
    public List<StudentDTO> getStudentDTO() {
        List<StudentDTO> response = new ArrayList<>();
        response = studentList.stream().map(student -> studentMapper.studentToStudentDTO(student)).collect(Collectors.toList());
        return response;
    }

}
