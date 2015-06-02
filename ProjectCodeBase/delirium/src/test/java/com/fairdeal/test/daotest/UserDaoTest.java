package com.fairdeal.test.daotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.dao.UserDao;
import com.fairdeal.entity.User;
import com.fairdeal.test.BaseTest;
import com.fairdeal.util.LoggerUtil;

public class UserDaoTest extends BaseTest {

	@Before
	public void setup() {
		super.setUp();
		deleteElementTable("USERROLES");
		deleteTable("User");
	}

	@Autowired
	UserDao userDaoRepo;

	@Test
	public void createSelectDeleteTest() {
		String email = "puneet@testemail.com";
		User user = new User();
		user.setEmailAddress(email);
		user.setFirstName("Puneet Behl");
		userDaoRepo.insertUser(user);

		User currentUser = userDaoRepo.getUser(email);
		assertNotNull("The agent selecet query is not working. Not able to fetch via emailId", currentUser);
		assertEquals(user, currentUser);
	}

	@Test
	public void testAgentDoesnotExist() {
		String email = "puneet@testemail.com";
		User currentUser = userDaoRepo.getUser(email);
		assertNull(currentUser);
	}

	@Test
	public void testFetchDeletedAgent() {
		String email = "puneet@testemail.com";
		User user = new User();
		user.setEmailAddress(email);
		user.setFirstName("Puneet Behl");
		userDaoRepo.insertUser(user);

		User currentUser = userDaoRepo.getUser(email);
		assertNotNull("The agent selecet query is not working. Not able to fetch via emailId", currentUser);
		userDaoRepo.DeleteUser(currentUser);

		User currentUser2 = userDaoRepo.getUser(email);
		assertNull(currentUser2);
	}

	@Test
	public void fetchAllUsers() {
		for (int i = 0; i < 100; i++) {
			String email = "puneet" + i + "@testemail.com";
			User user = new User();
			user.setEmailAddress(email);
			user.setFirstName("Puneet Behl" + i);
			userDaoRepo.insertUser(user);
		}

		List<User> list = userDaoRepo.getAllUsers();
		assertEquals(100, list.size());
		assertEquals("puneet4@testemail.com", list.get(4).getEmailAddress());

		List<User> listpagination = userDaoRepo.getAllUsers();
	}

	@Test
	public void fetchUserPagination() throws InterruptedException {
		int loop = 100;
		for (int i = 0; i < loop; i++) {
			Thread.sleep(100);
			String email = "puneet" + i + "@testemail.com";
			User user = new User();
			user.setEmailAddress(email);
			user.setFirstName("Puneet Behl" + i);
			userDaoRepo.insertUser(user);
		}

		List<User> list = userDaoRepo.getUsers(10, 20);
		assertEquals(20, list.size());
	}

	@Test
	public void testAddUserRoles() {
		int i=0;
		String email = "puneet" + i + "@testemail.com";
		User user = new User();
		List<String> userRoles = new LinkedList<String>();
		userRoles.add(Constants.User.ROLE_ADMIN);
		userRoles.add(Constants.User.ROLE_USER);
		user.setUserRoles(userRoles);
		user.setEmailAddress(email);
		user.setFirstName("Puneet Behl" + i);
		userDaoRepo.insertUser(user);
		
		User currentUser = userDaoRepo.getUser(email);
		LoggerUtil.debug(currentUser);
		assertNotNull(currentUser);
		assertTrue(currentUser.getUserRoles().contains(Constants.User.ROLE_ADMIN));
		assertTrue(currentUser.getUserRoles().contains(Constants.User.ROLE_USER));
		assertTrue(!currentUser.getUserRoles().contains(Constants.User.ROLE_BROKER));
	}
}
