package edu.deadshot.springbootcachebasic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.deadshot.springbootcachebasic.models.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {
    List<Student> findAll();
}
