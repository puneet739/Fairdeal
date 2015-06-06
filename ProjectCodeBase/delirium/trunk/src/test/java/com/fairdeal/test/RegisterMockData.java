package com.fairdeal.test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.dao.AgentDao;
import com.fairdeal.dao.ClassifiedDao;
import com.fairdeal.dao.UserDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.entity.Classified;
import com.fairdeal.entity.User;
import com.fairdeal.service.ClassifiedService;
import com.fairdeal.test.BaseTest;

public class RegisterMockData extends BaseTest {

	@Autowired
	AgentDao agentRepo;

	@Autowired
	UserDao userDao;

	@Autowired
	ClassifiedDao classifiedDao;

	@Autowired
	ClassifiedService classifiedService;
	@Test
	public void addAgentMock() {
		for (int i = 0; i < 10; i++) {
			Agent agent = new Agent();
			agent.setAgentDescription("TestAgent" + i);
			agent.setFirstName("Puneet" + i);
			agent.setLastName("Behl" + i);
			agent.setImageUrl("http://wp.production.patheos.com/blogs/freedhearts/files/2012/09/love_41.jpg");
			agentRepo.insertAgent(agent);
		}
	}

	@Test
	public void registerUser() {
		User user = new User();
		user.setEmailAddress("meghab");
		user.setFirstName("Rahul");
		user.setPassword("megha");
		List<String> userRoles = new LinkedList<String>();
		userRoles.add(Constants.User.ROLE_USER);
		user.setUserRoles(userRoles);
		user.setClassifiedQuota(100001);
		userDao.insertUser(user);
	}
	
	@Test
	public void registerAdminUser() {
		User user = new User();
		user.setEmailAddress("admin");
		user.setFirstName("admin");
		user.setPassword("admin");
		List<String> userRoles = new LinkedList<String>();
		userRoles.add(Constants.User.ROLE_USER);
		userRoles.add(Constants.User.ROLE_ADMIN);
		user.setClassifiedQuota(100001);
		user.setUserRoles(userRoles);
		userDao.insertUser(user);
	}
	
	@Test
	public void addUserData() {
		for (int i =0; i<10; i++){
		User user = new User();
		user.setEmailAddress("Puneet12"+i+"@gmail.com");
		user.setFirstName("Rahul");
		user.setPassword("rahul");
		List<String> userRoles = new LinkedList<String>();
		userRoles.add(Constants.User.ROLE_USER);
		user.setUserRoles(userRoles);
		userDao.insertUser(user);
		}
	}

	@Test
	public void addClassifieds() {
		deleteElementTable("USERROLES");
		deleteTable("User");
		
		long userId = registerUser("puneet739@gmail.com");
		for (int i = 0; i < 50; i++) {
			classifiedService.registerClassified("Test Short Title RENT: " + i, "This  is a  very classy property, Call me to get more information" + i,
					"House no 124, Sec 37, Faridabad", "Faridabad " + i, "Hayrana " +i, "121003", "9711616135" + i, null, "2000000",""+userId,Constants.Classified.TYPE_RENT, null);
		}
		for (int i = 0; i < 100; i++) {
			classifiedService.registerClassified("Test Short Title SELL: " + i, "This  is a  very classy property, Call me to get more information" + i,
					"House no 124, Sec 37, Faridabad", "Faridabad " + i, "Hayrana " +i, "121003", "9711616135" + i, null, "2000000",""+userId,Constants.Classified.TYPE_SELL, null);
		}
	}
}
