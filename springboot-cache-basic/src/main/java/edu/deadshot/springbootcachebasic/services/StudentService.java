package edu.deadshot.springbootcachebasic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import edu.deadshot.springbootcachebasic.models.Student;
import edu.deadshot.springbootcachebasic.repositories.StudentRepo;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;
    
    @Cacheable(value="books", key="#id")
    public Student getStudentByIdService(Long id) {
        try
        {
            System.out.println("Going to sleep for 5 Secs. id: " + id);
            Thread.sleep(1000*5);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        return studentRepo.findById(id).get(); 
    }

}
