package com.fairdeal.service;

import javax.transaction.Transactional;

import com.fairdeal.exception.AuthException;

@Transactional
public interface LoginService {

	public Integer doLogin(String username, String password, String currentEncoding, String targetEncoding) throws AuthException;
	
}
