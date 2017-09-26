package org.itstep.service;

import static org.junit.Assert.*;

import java.util.List;

import org.itstep.App;
import org.itstep.dao.pojo.Group;
import org.itstep.dao.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)

public class StudentSeviceTest {

	@Autowired
	StudentSevice studentSevice;

	@Autowired
	GroupService groupService;

	@Test
	public void testGetStudent() {
		Student student = new Student();
		student.setLogin("zera4ever");
		student.setPassword("123456");
		student.setFirstName("Zera");
		student.setLastName("Ametova");
		student.setGroupName("ST20");
		studentSevice.createAndUpdateStudent(student);
		Student studenFromtDB = studentSevice.getStudent(student.getLogin());
		assertEquals(student.getLogin(), studenFromtDB.getLogin());
	}

	@Test
	public void testCreateAndUpdateStudent() {
		Student student = new Student();
		student.setLogin("zera4ever");
		student.setPassword("123456");
		student.setFirstName("Zera");
		student.setLastName("Ametova");
		student.setGroupName("ST20");
		Student checkedStudent = studentSevice.createAndUpdateStudent(student);
		assertNotNull(checkedStudent);
	}

	@Test
	public void testDeleteStudent() {
		Student student = new Student();
		student.setLogin("zera4ever");
		student.setPassword("123456");
		student.setFirstName("Zera");
		student.setLastName("Ametova");
		student.setGroupName("ST20");
		Student studentFromDB = studentSevice.createAndUpdateStudent(student);
		studentSevice.deleteStudent(studentFromDB.getLogin());
		assertNull(studentSevice.getStudent(student.getLogin()));

	}

	@Test
	public void testFindStudentsByGroup() {
		Student student = new Student();
		student.setLogin("zera4ever");
		student.setPassword("123456");
		student.setFirstName("Zera");
		student.setLastName("Ametova");
		student.setGroupName("ST20");
		studentSevice.createAndUpdateStudent(student);
		List<Student> studentsFromDB = studentSevice.findStudentsByGroup(student.getGroupName());
		assertEquals(student.getGroupName(), studentsFromDB.get(0).getGroupName());
	}

	@Test
	public void testFindAllStudentsByCourse() {
		Student student = new Student();
		student.setLogin("zera4ever");
		student.setPassword("123456");
		student.setFirstName("Zera");
		student.setLastName("Ametova");
		student.setGroupName("ST20");
		studentSevice.createAndUpdateStudent(student);

		Group group = new Group();
		group.setCourse(5);
		group.setGroupName("ST20");
		Group groupFromDB = groupService.createAndUpdateGroup(group);

		List<Student> studentsFromDB = studentSevice.findAllStudentsByCourse(group.getCourse());
		assertTrue(!studentsFromDB.isEmpty());

	}

}
