package com.fairdeal.service;

import java.util.List;

import javax.transaction.Transactional;

import com.fairdeal.entity.UserQuery;

@Transactional
public interface UserQueryService {

	public void insertUserQuery(String firstname, String lastName, String phoneNumber, String email,String message);
	
	public void insertUserQuery(String firstname, String lastName, String phoneNumber, String email,String message, long agentId, long classifiedId);
	
	public void modifyQuery(UserQuery query);
	
	public void deleteQuery(long query);
	
	public UserQuery getUserQuery(long queryId);
	
	public List<UserQuery> getAllPendingQueries();
}
