package com.fairdeal.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.fairdeal.entity.User;

@Transactional
public interface UserService {

	public User getUser(long userId);
	
	public User getUser(String emailAddress);
	
	public List<User> getAllUsers(); 
	
	public void modifyUser(User user);
	
	public Integer registerUser(String firstName, String lastName, String emailAddress, String phoneNumber,Date dateOfBirth, String password);
	
	public Integer registerUser(String firstName, String lastName, String emailAddress, String phoneNumber, String dateOfBirth, String password);
	
	public void deletedUser(User user);
	
}
