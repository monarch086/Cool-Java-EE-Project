package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.itstep.App;
import org.itstep.dao.pojo.Group;
import org.itstep.service.GroupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.base.Verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class GroupControllerTest {
	
	@MockBean
	GroupService groupService;
	
	@Autowired
	TestRestTemplate testRestTemplate;
		
	Group group;
	
	@Before
	public void setTestData() {
		group = new Group();
		group.setGroupName("ST20");
		group.setCourse(3);
	}
	
	@Test
	public void testCreateGroup() {
		Mockito.when(groupService.isUnique(Mockito.<Group>any())).thenReturn(true);
		Mockito.when(groupService.createAndUpdateGroup(Mockito.<Group>any())).thenReturn(group);
		RequestEntity<Group> request = null;
		try {
			request = new RequestEntity<Group>(group, HttpMethod.POST, new URI("/group"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Group> response = testRestTemplate.exchange(request, Group.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Mockito.verify(groupService, Mockito.times(1)).createAndUpdateGroup(Mockito.<Group>any());
	}

	@Test
	public void testUpdateGroup() {
	}

	@Test
	public void testGetOneGroupByString() {
		Mockito.when(groupService.getGroup(Mockito.anyString())).thenReturn(group);
		RequestEntity<String> request = null;
		try {
			request = new RequestEntity<String>(HttpMethod.GET, 
					new URI("/group/get-group?groupName="+group.getGroupName()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Group> response = testRestTemplate.exchange(request, Group.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(groupService, Mockito.times(1)).getGroup(Mockito.<String>any());
	}

	@Test
	public void testGetOneGroupInt() {
		List<Group> groups = Arrays.asList(group);
		Mockito.when(groupService.findAllByCourse(Mockito.anyInt())).thenReturn(groups);
		RequestEntity<String> request = null;
		try {
			request = new RequestEntity<String>(HttpMethod.GET, 
					new URI("/group/get-grouplist?course="+group.getCourse()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<List<Group>> response = testRestTemplate
				.exchange(request, new ParameterizedTypeReference<List<Group>>() {});
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(groupService, Mockito.times(1)).findAllByCourse(Mockito.anyInt());
	}

	@Test
	public void testDeleteGroup() {
		Mockito.doNothing().when(groupService).deleteGroup(Mockito.anyString());
		RequestEntity<String> request = null;
		try {
			request = new RequestEntity<String>(HttpMethod.DELETE, 
					new URI("/group?groupName="+group.getGroupName()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity response = testRestTemplate.exchange(request, ResponseEntity.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(groupService, Mockito.times(1)).deleteGroup(Mockito.<String>any());
	}

}
