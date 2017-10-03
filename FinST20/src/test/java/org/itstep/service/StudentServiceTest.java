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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class StudentServiceTest {
	
	@Autowired
	StudentService studentService;

	@Test
	public void testGetStudent() {
		Student student = new Student();
		student.setLogin("Vasiliy17");
		student.setPassword("123456");
		student.setFirstName("Vasiliy");
		student.setLastName("Pupkin");
		student.setGroupName("ST-20");
		Student studentFromDB = studentService.save(student);
		assertNotNull(studentFromDB);
		Student checkedStudent = studentService.findOne(studentFromDB.getLastName());
		assertEquals ("Pupkin", checkedStudent.getLastName());
	}

	@Test
	public void testCreateAndUpdateStudent() {
		Student student = new Student();
		student.setLogin("Vasiliy17");
		student.setPassword("123456");
		student.setFirstName("Vasiliy");
		student.setLastName("Pupkin");
		student.setGroupName("ST-20");
		Student studentFromDB = studentService.save(student);
		assertNotNull(studentFromDB);
		Student studentFromDB = studentService.createAndUpdateStudent(student);
		Student chekedStudent = studentService.findOne(studentFromDB.getLogin());
		assertEquals("Vasiliy17", studentFromDB.getLogin());
	}

	@Test
	public void testDeleteStudent() {
		Student student = new Student();
		student.setLogin("Vasiliy17");
		student.setPassword("123456");
		student.setFirstName("Vasiliy");
		student.setLastName("Pupkin");
		student.setGroupName("ST-20");
		Student studentFromDB = studentService.save(student);
		studentService.deleteStudent(studentFromDB);
	    assertNull (studentFromDB);
	}

	@Test
	public void testFindStudentsByGroup() {
		Student student = new Student();
		student.setLogin("Vasiliy17");
		student.setPassword("123456");
		student.setFirstName("Vasiliy");
		student.setLastName("Pupkin");
		student.setGroupName("ST-20");
		List<Student> studentList = studentService.findStudentsByGroup("ST20");
		assertTrue (!studentList.isEmpty());
	}

	@Test
	public void testFindAllStudentsByCourse() {
		Student student = new Student();
		student.setLogin("Vasiliy17");
		student.setPassword("123456");
		student.setFirstName("Vasiliy");
		student.setLastName("Pupkin");
		student.setGroupName("ST-20");
		List<Student> studentList = studentService.findAllStudentsByCourse(course);
		assertTrue(!studentList.isEmpty());
					
	}

}
