package com.fairdeal.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fairdeal.entity.User;
import com.fairdeal.service.UserService;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;
import com.fairdeal.utility.SessionUtil;
import com.fairdeal.utility.Util;

@Component(value = "login")
@Scope(value = "session")
public class LoginBean implements Serializable{

	@Autowired
	private UserService userservice;

	@Autowired
	SessionUtil sessionUtil;

	private String username;
	private String password;
	
	public void doLogin() {
		LoggerUtil.debug("Username provided is " + username);
		LoggerUtil.debug("Password provided is " + password);
		try {
			AuthenticationManager authmanager=ObjectRepository.getContext().getBean("authenticationManager", AuthenticationManager.class);
			Authentication request = new UsernamePasswordAuthenticationToken(username, password);
			Authentication result = authmanager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			User userEntity = userservice.getUser(username);
			sessionUtil.serUserDetails(userEntity);
			Util.returntoSamePage();
		} catch (Exception e) {
			LoggerUtil.error("Exception caused", e);
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("Not able to SignIn. Username/Password did not match"));
		}
	}

	public void logout(){
		SecurityContextHolder.clearContext();
		sessionUtil.logout();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
