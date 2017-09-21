package org.itstep.service;

import static org.junit.Assert.*;

import org.itstep.dao.pojo.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;




public class StudentSeviceTest {
	
	@Autowired
	StudentSevice studentSevice;
	

	@Test
	public void testGetStudent() {
		Student studentFromDB;
		Student checkedStudent = studentSevice.findOne(studentFromDB.getLogin());
		assertNotNull(checkedStudent);
	}

	@Test
	public void testCreateAndUpdateStudent() {
		Student student = new Student();
		student.setLogin("zera4ever");
		student.setPassword("123456");
		student.setFirstName("Zera");
		student.setLastName("Ametova");
		student.setGroupName("ST20");
		Student studentFromDB = studentSevice.createAndUpdateStudent(student);
		assertNotNull(studentFromDB);
		
		
		
		
	}

	@Test
	public void testDeleteStudent() {
//		studentSevice.delete(studentFromDB.getLogin());
//		assertTrue(null);
		
	}

	@Test
	public void testFindStudentsByGroup() {
		
	}

	@Test
	public void testFindAllStudentsByCourse() {
		
	}

}
