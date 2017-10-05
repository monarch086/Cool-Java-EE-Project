package org.itstep.service;

import static org.junit.Assert.*;

import java.util.List;

import org.itstep.App;
import org.itstep.dao.pojo.Group;
import org.itstep.dao.pojo.Lesson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class LessonServiceTest {

	@Autowired
	LessonService lessonService;

	@Test
	public void testSaveAndUpdate() {
		Lesson lesson = new Lesson();
		lesson.setGroup("ST-20");
		lesson.setLength(45l);
		lesson.setLessonStart(System.currentTimeMillis());
		lesson.setRoom("A1");
		lesson.setSubject("QA");
		lesson.setTeacher("Sasha");
		Lesson lessonFromDB = lessonService.saveAndUpdate(lesson);
		assertNotNull(lessonFromDB);
		lessonService.delete(lessonFromDB.getLessonId());
	}

	@Test
	public void testGetOneByGroupAndStartTime() {
		Lesson lesson = new Lesson();
		lesson.setGroup("ST-20");
		lesson.setLength(45l);
		lesson.setLessonStart(System.currentTimeMillis());
		lesson.setRoom("A1");
		lesson.setSubject("QA");
		lesson.setTeacher("Sasha");
		Lesson lessonFromDB = lessonService.saveAndUpdate(lesson);
		Lesson lessonFiltered = lessonService.getOneByGroupAndStartTime(lessonFromDB.getGroup(), lessonFromDB.getLessonStart());
		assertEquals(lesson.getGroup(), lessonFromDB.getGroup());
		lessonService.delete(lessonFromDB.getLessonId());
	}

	@Test
	public void testGetOneByTeacherAndStartTime() {
		Lesson lesson = new Lesson();
		lesson.setGroup("ST-20");
		lesson.setLength(45l);
		lesson.setLessonStart(System.currentTimeMillis());
		lesson.setRoom("A1");
		lesson.setSubject("QA");
		lesson.setTeacher("Sasha");
		Lesson lessonFromDB = lessonService.saveAndUpdate(lesson);
		Lesson lessonFiltered = lessonService.getOneByTeacherAndStartTime(lessonFromDB.getTeacher(), lessonFromDB.getLessonStart());
		assertEquals(lesson.getTeacher(),lessonFromDB.getTeacher());
		lessonService.delete(lessonFromDB.getLessonId());
	}
	@Test
	public void testGetLessonsForCourseForPeriod() {
		Lesson lesson = new Lesson();
		lesson.setGroup("ST-20");
		lesson.setLength(45l);
		lesson.setLessonStart(System.currentTimeMillis());
		lesson.setRoom("A1");
		lesson.setSubject("QA");
		lesson.setTeacher("Sasha");
		Lesson lessonFromDB = lessonService.saveAndUpdate(lesson);
		List<Lesson> lessonFiltered = lessonService.getLessonsForCourseForPeriod(3, lessonFromDB.getLessonStart()-30000, lessonFromDB.getLessonStart()+lesson.getLength()+30000);
		assertEquals(lesson.getLessonStart(), lessonFromDB.getLessonStart());
		lessonService.delete(lessonFromDB.getLessonId());
	}
	@Test
	public void testGetLessonsForTeacherForPeriod () {
		Lesson lesson = new Lesson();
		lesson.setGroup("ST-20");
		lesson.setLength(45l);
		lesson.setLessonStart(System.currentTimeMillis());
		lesson.setRoom("A1");
		lesson.setSubject("QA");
		lesson.setTeacher("Sasha");
		Lesson lessonFromDB = lessonService.saveAndUpdate(lesson);
		List<Lesson> lessonFiltered = lessonService.getLessonsForTeacherForPeriod("Sasha", lessonFromDB.getLessonStart()-30000, lessonFromDB.getLessonStart()+lesson.getLength()+30000);
		assertEquals(lesson.getLength(),lessonFromDB.getLength());
		lessonService.delete(lessonFromDB.getLessonId());
	}
		
		
}

