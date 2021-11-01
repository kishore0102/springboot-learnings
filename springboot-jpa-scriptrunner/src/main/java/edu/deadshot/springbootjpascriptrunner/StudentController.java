package edu.deadshot.springbootjpascriptrunner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentRepo studentRepo;

	@GetMapping("/")
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

}
