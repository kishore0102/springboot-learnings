package edu.deadshot.springbootjpamapping.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.deadshot.springbootjpamapping.models.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Integer> {
    List<Student> findAll();    
}
