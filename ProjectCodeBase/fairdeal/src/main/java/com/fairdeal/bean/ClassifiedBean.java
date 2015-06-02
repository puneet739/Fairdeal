package com.fairdeal.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.Util;

@ManagedBean(name = "classifiedbean")
@ViewScoped
public class ClassifiedBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -1944697838987992111L;
	private String shortTitle;
	private String description;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String agentId;
	private String contactNumber;
	private String email;
	private BigDecimal price;
	private Date expiryDate;
	private double latitude;
	private double longitude;
	private String postedDate;

	private UserBean user;

	private boolean classifiedAccess;

	// This is the type of Sell or Rent
	private String type;

	private Map<String, String> params;

	private String image1Url;
	private String image2Url;
	private String image3Url;
	private String image4Url;

	private UploadedFile uploadedImage1;
	private UploadedFile uploadedImage2;
	private UploadedFile uploadedImage3;
	private UploadedFile uploadedImage4;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String submit() {
		LoggerUtil.debug("Submitting the Classified bean:" + toString());
		return "success";
	}

	public void uploadImage() {
		LoggerUtil.debug("Application is uploading image");
		LoggerUtil.debug(this);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void loadClassified() {
		LoggerUtil.debug("Calling from loadClassified method");
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

	public UploadedFile getUploadedImage4() {
		return uploadedImage4;
	}

	public void setUploadedImage4(UploadedFile uploadedImage4) {
		this.uploadedImage4 = uploadedImage4;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostedDate() {
		return Util.getStringFromDate(getCreatedDate());
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public boolean isClassifiedAccess() {
		return classifiedAccess;
	}

	public void setClassifiedAccess(boolean classifiedAccess) {
		this.classifiedAccess = classifiedAccess;
	}

	public String getImage1Url() {
		return image1Url;
	}

	public void setImage1Url(String image1Url) {
		this.image1Url = image1Url;
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

	public String getImage4Url() {
		return image4Url;
	}

	public void setImage4Url(String image4Url) {
		this.image4Url = image4Url;
	}

	public void handleImageUpload1(FileUploadEvent event) throws Exception {
		uploadedImage1 = event.getFile();
	}

	public void handleImageUpload2(FileUploadEvent event) {
		uploadedImage2 = event.getFile();
	}

	public void handleImageUpload3(FileUploadEvent event) {
		uploadedImage3 = event.getFile();
	}

	public void handleImageUpload4(FileUploadEvent event) {
		uploadedImage4 = event.getFile();
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

}
