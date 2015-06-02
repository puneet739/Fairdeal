package com.fairdeal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Results;
import com.fairdeal.dao.UserDao;
import com.fairdeal.entity.User;
import com.fairdeal.exception.AuthException;
import com.fairdeal.service.LoginService;

public class LoginServiceImpl implements LoginService{

	@Autowired
	UserDao userDao;
	
	@Override
	public Integer doLogin(String username, String password, String currentEncoding, String targetEncoding) throws AuthException{
		User user = userDao.getUser(username);
		if (user == null || user.getPassword() == null) return Results.User.USER_DOES_NOT_EXIST;
			String userPassword = user.getPassword();
			
		String passwordToMatch = getEncodingPassword(password,currentEncoding,targetEncoding);
		if (userPassword.equals(passwordToMatch)) return Results.SUCCESS;
		return null;
	}

	private String getEncodingPassword(String password, String currentEncoding, String targetEncoding) {
		if (currentEncoding.equals(targetEncoding)){
			return password;
		}
		return null;
	}
}
