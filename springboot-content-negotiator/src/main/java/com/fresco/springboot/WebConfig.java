package com.fresco.springboot;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecated")
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = WebConfig.class)
public class WebConfig extends WebMvcConfigurerAdapter {

	public WebConfig() {
		super();
	}

	// public void configureContentNegotiation(ContentNegotiationConfigurer
	// configurer) {
	// configurer.favorPathExtension(false).favorParameter(true).parameterName("format").ignoreAcceptHeader(true)
	// .useJaf(false).defaultContentType(MediaType.APPLICATION_JSON)
	// .mediaType("xml", MediaType.APPLICATION_XML).mediaType("json",
	// MediaType.APPLICATION_JSON);
	// }

}