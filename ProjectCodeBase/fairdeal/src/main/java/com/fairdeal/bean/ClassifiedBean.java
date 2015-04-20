package com.fairdeal.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.UploadedFile;

import com.fairdeal.utility.LoggerUtil;

@ManagedBean(name="classifiedbean")
@RequestScoped
public class ClassifiedBean implements Serializable{

	private static final long serialVersionUID = -1944697838987992111L;
	
	private String id;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String agentId;
	private String contactNumber;
	private BigDecimal price;
	private String description;
	private Date postedDate;
	private Date expiryDate;
	private double latitude;
	private double longitude;
	
	//This is the type of Sell or Rent
	private String type;	
	
	private UploadedFile file;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
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
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public String submit() {
		LoggerUtil.debug("Submitting the Classified bean:"+toString());
		return "success";
	}
	
	public void uploadImage() {
		LoggerUtil.debug("Application is uploading image");
		//LoggerUtil.debug("Uploaded File name is :: "+file.getFileName());
		LoggerUtil.debug(this);
	}
	
	@Override
	public String toString() {
		return "ClassifiedBean [id=" + id + ", address=" + address + ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", agentId=" + agentId + ", contactNumber=" + contactNumber + ", price=" + price + ", description=" + description + ", postedDate=" + postedDate + ", expiryDate=" + expiryDate + ", type=" + type + ", file=" + file + "]";
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
	
	public void loadClassified(){
		LoggerUtil.debug("Calling from loadClassified method");
	}
	
}
