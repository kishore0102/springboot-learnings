package edu.deadshot.WorkingWithRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<Student>();

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations redisOperations;

    public StudentService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisOperations = redisTemplate.opsForHash();
    }

    @PostConstruct
    public void init() {
        students.add(new Student(1, "harish", "11111", "email"));
        students.add(new Student(2, "kishore", "22222", "email"));
    }

    public List<Student> getAllStudents() {
        if(redisOperations.hasKey("STUDENT", "allStudents")) {
            return (List<Student>) redisOperations.get("STUDENT", "allStudents");
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String key = "allStudents";
        redisOperations.put("STUDENT", key, students);
        return students;
    }

    public Student getStudentByRollno(int rollno) {
        String rollnoString = String.valueOf(rollno);
        if(redisOperations.hasKey("STUDENT", rollnoString)) {
            return (Student) redisOperations.get("STUDENT", rollnoString);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Student student = students.stream().filter(s -> s.getRollno() == rollno).collect(Collectors.toList()).get(0);
        String key = String.valueOf(student.getRollno());
        redisOperations.put("STUDENT", key, student);
        return student;
    }

    public void addStudent(Student newStudent) {
        students.add(newStudent);
    }

}
