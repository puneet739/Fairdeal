package com.fairdeal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.dao.UserQueryDao;
import com.fairdeal.entity.UserQuery;
import com.fairdeal.service.UserQueryService;
import com.fairdeal.service.UserService;

public class UserQueryServiceImpl implements UserQueryService{

	@Autowired
	private UserQueryDao userQueryDao;
	
	@Autowired
	UserService service;
	
	@Override
	public void insertUserQuery(String firstname, String lastName, String phoneNumber, String email, String message) {
		UserQuery query = getUserQueryObject(firstname, lastName, phoneNumber, email, message);
		userQueryDao.insertUserQuery(query);
	}

	@Override
	public void modifyQuery(UserQuery query) {
		userQueryDao.modifyUserQuery(query);	
	}

	@Override
	public void deleteQuery(long query) {
		UserQuery userQuery = getUserQuery(query);
		userQueryDao.deleteUserQuery(userQuery);
	}

	@Override
	public UserQuery getUserQuery(long queryId) {
		return userQueryDao.getQuery(queryId);
	}

	@Override
	public List<UserQuery> getAllPendingQueries() {
		List<UserQuery> list = userQueryDao.getUserQueries("from UserQuery where answered="+Constants.UserQuery.QUERY_UNANSWERED+" order by updatedDate");
		return list;
	}

	@Override
	public void insertUserQuery(String firstname, String lastName, String phoneNumber, String email, String message, long agentId, long classifiedId) {
		UserQuery userQuery = getUserQueryObject(firstname, lastName, phoneNumber, email, message);
		userQuery.setAgentId(agentId);
		userQuery.setClassifiedId(classifiedId);
		userQueryDao.insertUserQuery(userQuery);
	}
	
	public UserQuery getUserQueryObject(String firstname, String lastName, String phoneNumber, String email, String message){
		UserQuery query = new UserQuery();
		query.setFirstName(firstname);
		query.setLastName(lastName);
		query.setContactNumber(phoneNumber);
		query.setEmailAddress(email);
		query.setMessage(message);
		return query;
	}
	
}
