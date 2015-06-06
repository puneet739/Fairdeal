package com.fairdeal.bean;

import java.util.LinkedList;
import java.util.List;

import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "agent")
@Scope(value="request")
public class AgentBean extends BaseBean {

	private String firstname;
	private String lastname;
	private String address;
	private String description;
	private String emailId;
	private String website;
	private String contactNumber;
	
	private String mainImageUrl;
	private String imageUrl;
	private String image2Url;
	private String image3Url;
	
	private UploadedFile uploadedMainImage;
	private UploadedFile uploadedImage1;
	private UploadedFile uploadedImage2;
	private UploadedFile uploadedImage3;
	
	private List<UploadedFile> allFiles= new LinkedList<UploadedFile>();


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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMainImageUrl() {
		return mainImageUrl;
	}

	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}

	public String getImage2Url() {
		return image2Url;
	}

	public void setImage2Url(String image2Url) {
		this.image2Url = image2Url;
	}

	public String getImage3Url() {
		return image3Url;
	}

	public void setImage3Url(String image3Url) {
		this.image3Url = image3Url;
	}

	public UploadedFile getUploadedMainImage() {
		return uploadedMainImage;
	}

	public void setUploadedMainImage(UploadedFile uploadedMainImage) {
		this.uploadedMainImage = uploadedMainImage;
	}

	public UploadedFile getUploadedImage1() {
		return uploadedImage1;
	}

	public void setUploadedImage1(UploadedFile uploadedImage1) {
		this.uploadedImage1 = uploadedImage1;
	}

	public UploadedFile getUploadedImage2() {
		return uploadedImage2;
	}

	public void setUploadedImage2(UploadedFile uploadedImage2) {
		this.uploadedImage2 = uploadedImage2;
	}

	public UploadedFile getUploadedImage3() {
		return uploadedImage3;
	}

	public void setUploadedImage3(UploadedFile uploadedImage3) {
		this.uploadedImage3 = uploadedImage3;
	}

	public List<UploadedFile> getAllFiles() {
		return allFiles;
	}

	public void setAllFiles(List<UploadedFile> allFiles) {
		this.allFiles = allFiles;
	}

}
