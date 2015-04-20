package com.fairdeal.bean;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.springframework.faces.webflow.JsfUtils;

import com.fairdeal.utility.LoggerUtil;

@ManagedBean(name = "agent")
@RequestScoped
public class AgentBean {

	private String id;
	private String name;
	private String address;
	private String imageUrl;
	private String description;
	private String emailId;
	private String website;
	private String agentPriority;
	private BigDecimal contactNumber;
	private UploadedFile uploadedImage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(BigDecimal contactNumber) {
		this.contactNumber = contactNumber;
	}

	public UploadedFile getUploadedImage() {
		return uploadedImage;
	}

	public void setUploadedImage(UploadedFile uploadedImage) {
		this.uploadedImage = uploadedImage;
	}

	@Override
	public String toString() {
		return "AgentBean [id=" + id + ", name=" + name + ", address=" + address + ", imageUrl=" + imageUrl + ", description=" + description + ", contactNumber=" + contactNumber + ", uploadedImage=" + uploadedImage + "]";
	}

	public void doSubmit(){
		FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("User Registered Succesfully"));
		LoggerUtil.debug("Submiting the Agent Bean: "+this);
		
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAgentPriority() {
		return agentPriority;
	}

	public void setAgentPriority(String agentPriority) {
		this.agentPriority = agentPriority;
	}
}
