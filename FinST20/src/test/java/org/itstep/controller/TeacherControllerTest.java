package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.itstep.App;
import org.itstep.dao.pojo.Teacher;
import org.itstep.service.TeacherService;
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
public class TeacherControllerTest {

	@MockBean
	TeacherService teacherService;

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	public void testCreateTeacher() {
		Teacher teacher = new Teacher();
		teacher.setFirstName("Maria");
		teacher.setLastName("Ivanova");
		teacher.setLogin("imariai");
		teacher.setPassword("123456789");
		teacher.setSubject("subject");
		Mockito.when(teacherService.getTeacher(Mockito.anyString())).thenReturn(teacher);
		RequestEntity<String> requestEntity = null;
		try {
			requestEntity = new RequestEntity<String>(HttpMethod.GET,
					new URI("/teacher/get-teacher?teacherName=" + teacher.getLogin()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Teacher> responseEntity = testRestTemplate.exchange(requestEntity, Teacher.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		Mockito.verify(teacherService, Mockito.times(1)).getTeacher(Mockito.anyString());

	}
//
//	@Test
//	public void testUpdateTeacher() {
//		Teacher teacher = new Teacher();
//		teacher.setFirstName("Maria");
//		teacher.setLastName("Ivanova");
//		teacher.setLogin("imariai");
//		teacher.setPassword("123456789");
//		teacher.setSubject("subject");
//
//		Mockito.when(teacherService.isUnique(Mockito.<Teacher>anyString())).thenReturn(false);
//		Mockito.when(teacherService.createAndUpdateTeacher(Mockito.<Teacher>anyString())).thenReturn(teacher);
//
//		RequestEntity<Teacher> requestEntity = null;
//		try {
//			requestEntity = new RequestEntity<Teacher>(teacher, HttpMethod.PUT, new URI("/teacher"));
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//		ResponseEntity<Teacher> responseEntity = testRestTemplate.exchange(requestEntity, Teacher.class);
//		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//		Mockito.verify(teacherService, Mockito.times(1)).createAndUpdateTeacher(Mockito.<Teacher>anyString());
//
//	}

}
