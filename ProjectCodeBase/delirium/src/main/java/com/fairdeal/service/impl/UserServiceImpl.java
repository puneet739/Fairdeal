package com.fairdeal.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.Results;
import com.fairdeal.dao.UserDao;
import com.fairdeal.entity.User;
import com.fairdeal.service.UserService;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.Util;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDao daoService;

	@Override
	public User getUser(String emailAddress) {
		User user = daoService.getUser(emailAddress);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = daoService.getAllUsers();
		return allUsers;
	}

	@Override
	public void modifyUser(User user) {
		user.setUpdatedDate(new Date());
		daoService.updateUser(user);
	}

	@Override
	public void deletedUser(User user) {
		user.setDeleted(Constants.User.AGENT_DELETED);
		modifyUser(user);
	}

	@Override
	public Integer registerUser(String firstName, String lastName, String emailAddress, String phoneNumber, Date dateOfBirth, String password) {
		try {
			User user = generateUserEntity(firstName, lastName, emailAddress, phoneNumber, dateOfBirth, null, password);
			daoService.insertUser(user);
		} catch (Exception e) {
			LoggerUtil.debug("Excpetion caused while Saving this agent",e);
			return Results.User.USER_ALREADY_REGISTERED;
		}
		return Results.SUCCESS;
	}

	@Override
	public Integer registerUser(String firstName, String lastName, String emailAddress, String phoneNumber, String dateOfBirth, String password) {
		return registerUser(firstName, lastName, emailAddress, phoneNumber, Util.getDateFromString(dateOfBirth), password);
	}

	private User generateUserEntity(String firstName, String lastName, String emailAddress, String phoneNumber, Date dateOfBirth, List<String> userRoles, String password) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmailAddress(emailAddress);
		user.setPhoneNumber(phoneNumber);
		user.setDateOfBirth(dateOfBirth);
		if (userRoles == null) {
			userRoles = new LinkedList<String>();
			userRoles.add(Constants.User.ROLE_USER);
		}
		user.setUserRoles(userRoles);
		user.setUpdatedDate(new Date());
		user.setCreatedDate(new Date());
		user.setPassword(password);
		user.setClassifiedQuota(Config.getInt(Constants.Config.CLASSIFIED_QUOTA_ORIGINAL));
		return user;
	}

	@Override
	public User getUser(long userId) {
		User user = daoService.getUser(userId);
		return user;
	}

}
