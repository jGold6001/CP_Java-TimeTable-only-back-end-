package org.itstep.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.itstep.App;
import org.itstep.dao.pojo.Group;
import org.itstep.service.GroupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroupControllerTest {
	
	@MockBean
	private GroupService groupService;
	
	@InjectMocks
	private GroupController groupController;
	
	@Spy
	List<Group> groups= new ArrayList<Group>();
	
	@Before
	public  void setUp(){
		MockitoAnnotations.initMocks(this);
		groups = getArray();
	}
	
	public List<Group> getArray(){	
		groups.add(new Group("12pv5",3));
		groups.add(new Group("15pv5",2));
		groups.add(new Group("14pv5",2));
		return groups;
	}
	
	@Test
	public void testSaveGroup() {
		Group group = new Group("10ui", 4);
		when(groupService.createAndUpdateGroup(group)).thenReturn(group);
		when(groupService.isUnique(Mockito.<Group>any())).thenReturn(true);	
		ResponseEntity<Group> responseEntity= groupController.createGroup(group);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	public void testUpdateGroup() {
		Group group = groups.get(1);
		when(groupService.createAndUpdateGroup(group)).thenReturn(group);
		ResponseEntity<Group> responseEntity= groupController.updateGroup(group);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testGetGroup() {
		Group group = groups.get(1);
		when(groupService.getGroup(group.getGroupName())).thenReturn(group);
		ResponseEntity<Group> responseEntity = groupController.getOneGroup(group.getGroupName());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testGetGroupsByCourse() {
		List<Group> returnedGroups = new ArrayList<Group>(Arrays.asList(groups.get(1), groups.get(2)));
		when(groupService.findAllByCourse(2)).thenReturn(returnedGroups);
		ResponseEntity<List<Group>> responseEntity = groupController.getGroupsByCourse(2);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	public void testDeleteGroup() {
		Group group = groups.get(2);
		doNothing().when(groupService).deleteGroup(group.getGroupName());
		ResponseEntity responseEntity = groupController.deleteGroup(group.getGroupName());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}