package org.itstep.dao;

import static org.junit.Assert.*;
import java.util.List;
import org.itstep.App;
import org.itstep.dao.pojo.Group;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class GroupDAOTest {
	
	@Autowired
	GroupDAO groupDAO;
	
	private Group groupFromDB;
	
	@Before
	public void setTestData() {
		Group group = new Group();
		group.setGroupName("ST20");
		group.setCourse(3);
		groupFromDB = groupDAO.saveAndFlush(group);
	}
	
	@After
	public void deleteTestData() {
		groupDAO.delete(groupFromDB.getGroupName());
	}
	
	@Test
	public void testFindAllByCourse() {
		List<Group> groupList = groupDAO.findAllByCourse(3);
		assertTrue(!groupList.isEmpty());
	}
	
	@Test
	public void testCreateAndSaveAndDeleteGroup() {
		Group checkedGroup = groupDAO.findOne(groupFromDB.getGroupName());
		assertNotNull(checkedGroup);
		assertEquals(3, groupFromDB.getCourse());
	}
}
