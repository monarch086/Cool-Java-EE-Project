package org.itstep.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.net.URI;
import java.net.URISyntaxException;

import org.itstep.App;
import org.itstep.dao.pojo.Student;
import org.itstep.dao.pojo.Subject;
import org.itstep.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
	@MockBean
	StudentService studentService;
	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	public void testCreateStudent() {

	}

	@Test
	public void testUpdateStudent() {

	}

	@Test
	public void testGetOneStudent() {

	}

	@Test
	public void testGetStudents() {

	}

	@Test
	public void testDeleteStudent() {
		Student student = new Student();
		student.setFirstName("Ivanvo");
		student.setGroupName("st20");
		student.setLastName("Ganett");
		student.setLogin("IvIva");
		student.setPassword("123456");
		Mockito.doNothing().when(studentService).deleteStudent(Mockito.<String>any());
		RequestEntity<String> request = null;
		try {
			request = new RequestEntity<String>(HttpMethod.DELETE,
					new URI("/student?studentName=" + student.getLogin()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity response = testRestTemplate.exchange(request, ResponseEntity.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(studentService, times(1)).deleteStudent(Mockito.anyString());
	}

}
