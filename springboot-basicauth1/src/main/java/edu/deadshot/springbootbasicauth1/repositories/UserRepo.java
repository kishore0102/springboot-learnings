package edu.deadshot.springbootbasicauth1.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.deadshot.springbootbasicauth1.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
}
