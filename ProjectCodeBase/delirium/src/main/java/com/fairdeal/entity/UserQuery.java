package com.fairdeal.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fairdeal.Constants;

@Entity
@Table(name = "USER_QUERY")
public class UserQuery extends BaseEntity {

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String contactNumber;
	private String message;
	private long agentId;
	private long classifiedId;
	private int answered;

	public UserQuery(){
		answered=Constants.UserQuery.QUERY_UNANSWERED;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getAnswered() {
		return answered;
	}

	public void setAnswered(int answered) {
		this.answered = answered;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public long getClassifiedId() {
		return classifiedId;
	}

	public void setClassifiedId(long classifiedId) {
		this.classifiedId = classifiedId;
	}

}
