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
		Lesson lessonFiltered = lessonService.getOneByGroupAndStartTime("ST-20", lessonFromDB.getLessonStart());
		assertEquals(lesson.getGroup(),lesson.getLessonStart(), lesson.getSubject());
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
		Lesson lessonFiltered = lessonService.getOneByTeacherAndStartTime("Sasha", lessonFromDB.getLessonStart());
		assertEquals(lesson.getTeacher(),lesson.getLessonStart(), lesson.getSubject());
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
		List<Lesson> lessonFiltered = lessonService.getLessonsForCourseForPeriod(3, lesson.getLessonStart()-30000, lesson.getLessonStart()+lesson.getLength()+30000);
		assertEquals(lesson.getGroup(),lesson.getLessonStart(), lesson.getRoom());
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
		List<Lesson> lessonFiltered = lessonService.getLessonsForTeacherForPeriod("Sasha", lesson.getLessonStart()-30000, lesson.getLessonStart()+lesson.getLength()+30000);
		assertEquals(lesson.getGroup(),lesson.getLessonStart(), lesson.getTeacher());
		lessonService.delete(lessonFromDB.getLessonId());
	}
		
		
}

