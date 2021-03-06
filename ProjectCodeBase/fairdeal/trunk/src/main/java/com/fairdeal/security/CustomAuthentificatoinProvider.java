package com.fairdeal.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.fairdeal.Results;
import com.fairdeal.entity.User;
import com.fairdeal.exception.AuthException;
import com.fairdeal.service.LoginService;
import com.fairdeal.service.UserService;
import com.fairdeal.util.LoggerUtil;

public class CustomAuthentificatoinProvider implements AuthenticationProvider {

	@Autowired
	private UserService service;

	@Autowired
	LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LoggerUtil.debug("Authentificating the User page");
		String name = authentication.getName();
		String password = (String) authentication.getCredentials();
		try {
			int result = loginService.doLogin(name, password, "plain", "plain");
			if (result==Results.User.USER_DOES_NOT_EXIST){
				throw new AuthException("User does not exist");
			}
			if (result == Results.SUCCESS) {
				User userEntity = service.getUser(name);
				List<GrantedAuthority> grantedAuths = new ArrayList();
				if (userEntity != null) {
					for (String roles : userEntity.getUserRoles()) {
						grantedAuths.add(new SimpleGrantedAuthority(roles));
					}
					Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
					return auth;
				}
			}
		} catch (AuthException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
