package com.fairdeal.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fairdeal.Constants;

@Entity
@Table(name="USER")
public class User extends BaseEntity {

	@Column(unique=true)
	private String emailAddress;
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String imageUrl;
	private Date dateOfBirth;
	private int classifiedQuota;
	private int userCategory;
	
	private long agentId;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="USERROLES", joinColumns=@JoinColumn(name="id"))
	@Column(name="roles")
	public List<String> userRoles;
	
	private String password;
	
	//Set to 1 if agent dont want mails and 0 if agent can be allowed mails from us
	private int mailingList=Constants.User.MAILER_ENABLED;
	
	//This is used to login the user who is confirmed. 
	private int confirmed=0;
	
	private Date lastLogin;

	public User(){
		setDeleted(Constants.User.AGENT_NOT_DELETED);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}


	public int getMailingList() {
		return mailingList;
	}

	public void setMailingList(int mailingList) {
		this.mailingList = mailingList;
	}

	@Override
	public String toString() {
		return "User [emailAddress=" + emailAddress + ", firstName=" + firstName + ", phoneNumber=" + phoneNumber + ", imageUrl=" + imageUrl + ", dateOfBirth=" + dateOfBirth + ", password=" + password + ", mailingList=" + mailingList + "]";
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getClassifiedQuota() {
		return classifiedQuota;
	}

	public void setClassifiedQuota(int classifiedQuota) {
		this.classifiedQuota = classifiedQuota;
	}

	public int getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(int userCategory) {
		this.userCategory = userCategory;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public boolean isHavingQuota() {
		return classifiedQuota>0;
	}

	public void decreaseQuota() {
		--classifiedQuota;
	}

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


}
