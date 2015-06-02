package com.fairdeal.test.servicetest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.entity.UserQuery;
import com.fairdeal.service.UserQueryService;
import com.fairdeal.test.BaseTest;
import com.fairdeal.util.LoggerUtil;

public class UserQueryTest extends BaseTest{

	@Autowired
	UserQueryService service;
	
	private String firstName ="Puneet";
	private String lastName="Behl";
	private String emailAddress="puneet739@gmail.com";
	private String contactNumber="9711616135";
	private String message="This is a test message";
	
	@Test
	public void testSubmitQuery(){
		List<UserQuery> queries = service.getAllPendingQueries();
		assertEquals(0, queries.size());
		
		LoggerUtil.debug("Runing from Submit Query Test");
		service.insertUserQuery(firstName, lastName, contactNumber, emailAddress, message);
		
		queries=service.getAllPendingQueries();
		assertEquals(1, queries.size());
		assertEquals(message, queries.get(0).getMessage());
	}
	
	@Test
	public void testDeleteQuery(){
		List<UserQuery> queries = service.getAllPendingQueries();
		assertEquals(0, queries.size());
		
		service.insertUserQuery(firstName, lastName, contactNumber, emailAddress, message);
		
		queries=service.getAllPendingQueries();
		assertEquals(1, queries.size());
		assertEquals(message, queries.get(0).getMessage());
		UserQuery quer1= queries.get(0);
		quer1.setAnswered(Constants.UserQuery.QUERY_ANSWERED);
		
		service.modifyQuery(quer1);
		
		queries=service.getAllPendingQueries();
		assertEquals(0, queries.size());
	}
	
	public void setUp(){
		deleteTable("UserQuery");
	}
}
