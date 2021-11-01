package edu.deadshot.springbootbasicauth1;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.deadshot.springbootbasicauth1.models.User;
import edu.deadshot.springbootbasicauth1.repositories.UserRepo;

@SpringBootApplication
public class SpringbootBasicauth1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBasicauth1Application.class, args);
	}

	@Autowired
	UserRepo userRepo;

	@PostConstruct
	public void init() {
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword("admin");
		user1.setRoles("ROLE_ADMIN,ROLE_USER");
		userRepo.save(user1);

		User user2 = new User();
		user2.setUsername("user");
		user2.setPassword("user");
		user2.setRoles("ROLE_USER");
		userRepo.save(user2);
	}
	
}
