package com.fairdeal.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "userquery")
@Scope("request")
public class UserQueryBean implements Serializable{

	private String firstName;
	private String lastName;
	private String userContactNumber;
	private String userEmailAddress;
	private String optionalMessage;
	private long classifiedId;
	private long agentId;
	
	public String getUserContactNumber() {
		return userContactNumber;
	}

	public void setUserContactNumber(String userContactNumber) {
		this.userContactNumber = userContactNumber;
	}

	public String getUserEmailAddress() {
		return userEmailAddress;
	}

	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}

	public String getOptionalMessage() {
		return optionalMessage;
	}

	public void setOptionalMessage(String optionalMessage) {
		this.optionalMessage = optionalMessage;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}
	
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public long getClassifiedId() {
		return classifiedId;
	}

	public void setClassifiedId(long classifiedId) {
		this.classifiedId = classifiedId;
	}

}
