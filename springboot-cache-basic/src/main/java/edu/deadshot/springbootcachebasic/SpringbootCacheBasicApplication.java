package edu.deadshot.springbootcachebasic;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import edu.deadshot.springbootcachebasic.models.Student;
import edu.deadshot.springbootcachebasic.repositories.StudentRepo;

@SpringBootApplication
@EnableCaching
public class SpringbootCacheBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCacheBasicApplication.class, args);
	}

	@Autowired
	StudentRepo studentRepo;

	@PostConstruct
	public void init() {
		studentRepo.save(new Student("name 1", "2020", "vellore"));
		studentRepo.save(new Student("name 2", "2020", "vellore"));
		studentRepo.save(new Student("name 3", "2020", "vellore"));
	}

}
