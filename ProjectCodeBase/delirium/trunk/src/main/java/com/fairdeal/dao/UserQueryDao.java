package com.fairdeal.dao;

import java.util.List;

import com.fairdeal.entity.UserQuery;

public interface UserQueryDao {
	
	public void insertUserQuery(UserQuery query);
	
	public void modifyUserQuery(UserQuery query);
	
	public void deleteUserQuery(UserQuery query);
	
	public UserQuery getQuery(long queryId);
	
	public List<UserQuery> getUserQueries(String sqlQuery);
}
