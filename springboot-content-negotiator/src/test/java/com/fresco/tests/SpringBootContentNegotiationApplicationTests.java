package com.fresco.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fresco.springboot.SpringBootContentNegotiationApplication;
import com.fresco.springboot.controller.MyController;
import com.fresco.springboot.model.Student;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.xml.XmlPage;

@TestMethodOrder(Alphanumeric.class)
@SpringBootTest(classes = {
		SpringBootContentNegotiationApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class SpringBootContentNegotiationApplicationTests {

	@LocalServerPort
	int port;
	RestTemplate template = new RestTemplate();
	final WebClient webClient = new WebClient();

	public String getString(InputStream is) throws IOException {
		int i;
		StringBuilder s = new StringBuilder();
		while ((i = is.read()) != -1)
			s.append((char) i);
		return s.toString();
	}

	@Test
	public void test1_invalidGet() {
		try {
			UnexpectedPage unPage = webClient.getPage("http://localhost:" + port + "/students.json");
			String s = getString(unPage.getInputStream());
			assertEquals(s, "[]");
			XmlPage xmlPage = webClient.getPage("http://localhost:" + port + "/student/10.xml");
			assertEquals(xmlPage.getElementsByTagName("attendance").get(0).asText(), "0.0");
			assertEquals(xmlPage.getElementsByTagName("rollNo").get(0).asText(), "0");
			unPage = webClient.getPage("http://localhost:" + port + "/student/10.json");
			s = getString(unPage.getInputStream());
			JSONObject json = new JSONObject(s);
			assertEquals(json.getInt("rollNo"), 0);
			assertEquals(json.getString("name"), "null");
			assertEquals(json.getString("department"), "null");
			assertEquals(json.get("attendance").toString(), "0.0");
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}

	@Test
	public void test2_createStudent() {
		try {
			Student student = new Student(100, "Jack Sparrow", "Pirate", 30.44);
			ResponseEntity<String> res = template.postForEntity("http://localhost:" + port + "/student", student,
					String.class);
			assertEquals(res.getStatusCodeValue(), 201);
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}

	@Test
	public void test3_getStudent() {
		try {
			XmlPage xmlPage = webClient.getPage("http://localhost:" + port + "/student/100.xml");
			assertEquals(xmlPage.getElementsByTagName("attendance").get(0).asText(), "30.44");
			assertEquals(xmlPage.getElementsByTagName("rollNo").get(0).asText(), "100");
			assertEquals(xmlPage.getElementsByTagName("name").get(0).asText(), "Jack Sparrow");
			assertEquals(xmlPage.getElementsByTagName("department").get(0).asText(), "Pirate");
			UnexpectedPage unPage = webClient.getPage("http://localhost:" + port + "/student/100.json");
			String s = getString(unPage.getInputStream());
			JSONObject json = new JSONObject(s);
			assertEquals(json.getInt("rollNo"), 100);
			assertEquals(json.getString("name"), "Jack Sparrow");
			assertEquals(json.getString("department"), "Pirate");
			assertEquals(json.get("attendance").toString(), "30.44");
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}

	@Test
	public void test5_updateStudent() {
		try {
			Student student = new Student(100, "Will Turner", "Pirate", 50.88);
			template.put("http://localhost:" + port + "/student/100", student);
			XmlPage xmlPage = webClient.getPage("http://localhost:" + port + "/student/100.xml");
			assertEquals(xmlPage.getElementsByTagName("attendance").get(0).asText(), "50.88");
			assertEquals(xmlPage.getElementsByTagName("rollNo").get(0).asText(), "100");
			assertEquals(xmlPage.getElementsByTagName("name").get(0).asText(), "Will Turner");
			assertEquals(xmlPage.getElementsByTagName("department").get(0).asText(), "Pirate");
			UnexpectedPage unPage = webClient.getPage("http://localhost:" + port + "/student/100.json");
			String s = getString(unPage.getInputStream());
			JSONObject json = new JSONObject(s);
			assertEquals(json.getInt("rollNo"), 100);
			assertEquals(json.getString("name"), "Will Turner");
			assertEquals(json.getString("department"), "Pirate");
			assertEquals(json.get("attendance").toString(), "50.88");
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}

	@Autowired
	MyController controller;

	@Test
	public void test6_deleteUser() {
		try {
			template.delete("http://localhost:" + port + "/student/100");
			XmlPage xmlPage = webClient.getPage("http://localhost:" + port + "/student/100.xml");
			assertEquals(xmlPage.getElementsByTagName("attendance").get(0).asText(), "0.0");
			assertEquals(xmlPage.getElementsByTagName("rollNo").get(0).asText(), "0");
			UnexpectedPage unPage = webClient.getPage("http://localhost:" + port + "/student/100.json");
			String s = getString(unPage.getInputStream());
			JSONObject json = new JSONObject(s);
			assertEquals(json.getInt("rollNo"), 0);
			assertEquals(json.getString("name"), "null");
			assertEquals(json.getString("department"), "null");
			assertEquals(json.get("attendance").toString(), "0.0");
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}

}
