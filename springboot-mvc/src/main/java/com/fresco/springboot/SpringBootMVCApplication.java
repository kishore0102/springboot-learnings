package com.fresco.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@EnableWebMvc
public class SpringBootMVCApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMVCApplication.class, args);
	}

	@Bean
	public ViewResolver viewResolver() {
	  InternalResourceViewResolver irv = new InternalResourceViewResolver();
	//   irv.setPrefix("/WEB-INF/");
	  irv.setSuffix(".jsp");
	  return irv;
	}
	
}
