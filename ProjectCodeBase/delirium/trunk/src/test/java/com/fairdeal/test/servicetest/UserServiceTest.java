package com.fairdeal.test.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Results;
import com.fairdeal.entity.User;
import com.fairdeal.service.UserService;
import com.fairdeal.test.BaseTest;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.Util;

public class UserServiceTest extends BaseTest{

	private String firstName = "Puneet";
	private String lastName = "Behl";
	private String  emailAddress= "Puneet@gmail.com";
	private String phoneNumber = "9711616135";
	private String dateOfBirth = "01/12/2014"; 	//The format is DD/MM/yyyy
	private Date dateOfBithDateFormat =Util.getCurrentDate();
	
	@Autowired
	UserService service;
	
	@Override
	public void setUp() {
		super.setUp();
		deleteElementTable("USERROLES");
		deleteTable("User");
	}
	
	@Test
	public void test(){}
	
	@Test
	public void testRegisterUsergetRoles(){
		int result = service.registerUser(firstName, lastName, emailAddress, phoneNumber, dateOfBirth, null);
		User user = service.getUser(emailAddress);
		assertEquals(emailAddress,user.getEmailAddress());
		assertNotNull(user.getId());
		assertTrue(user.getId()>0);
		assertEquals(Results.SUCCESS, result);
		LoggerUtil.debug(user.getUserRoles());
	}
	
	@Test
	public void testRegisterUserDateString(){
		int result = service.registerUser(firstName, lastName, emailAddress, phoneNumber, dateOfBirth, null);
		User user = service.getUser(emailAddress);
		assertEquals(emailAddress,user.getEmailAddress());
		assertNotNull(user.getId());
		assertTrue(user.getId()>0);
		assertEquals(Results.SUCCESS, result);
	}
	
	@Test
	public void testRegisterUserDateFormat(){
		int result = service.registerUser(firstName, lastName, emailAddress, phoneNumber, dateOfBithDateFormat, null);
		User user = service.getUser(emailAddress);
		assertEquals(emailAddress,user.getEmailAddress());
		assertNotNull(user.getId());
		assertTrue(user.getId()>0);
	}
	
	//This test is failing due to some reason, Will look into this soon. 
	@Test
	public void testRegisterUserAlreadyRegistered(){
		Integer result = service.registerUser(firstName, lastName, emailAddress, phoneNumber, dateOfBithDateFormat, null);
		User user = service.getUser(emailAddress);
		assertEquals(emailAddress,user.getEmailAddress());
		assertNotNull(user.getId());
		assertTrue(user.getId()>0);
		
		int result2 = service.registerUser(firstName, lastName, emailAddress, phoneNumber, dateOfBithDateFormat, null);
		assertEquals( Results.User.USER_ALREADY_REGISTERED,result2);
	}
	
	@Test
	public void testUserDoesNotExist(){
		User user = service.getUser(emailAddress);
		assertNull(user);
	}
	
}
