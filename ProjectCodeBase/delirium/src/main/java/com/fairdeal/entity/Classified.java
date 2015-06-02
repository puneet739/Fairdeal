package com.fairdeal.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name="CLASSIFIED")
@FilterDefs({
	@FilterDef(name="classifiedType",parameters=@ParamDef(name="type",type="integer")),
	@FilterDef(name="classifiedDeleted",parameters=@ParamDef(name="deleted",type="integer"))
})
@Filters({	@Filter(name="classifiedType" ,condition="type = :type"),
			@Filter(name="classifiedDeleted", condition="deleted = :deleted")
})
public class Classified extends BaseEntity {

	private String shorttitle;
	@Lob
	private String description;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String userId;
	private String contactNumber;
	private String email;
	private BigDecimal price;
	private Date expiryDate;
	
	//This will be having type, Sell/Rent
	private int type;
	
	//This is the build up area of that classified
	private String builtUpArea;
	
	private String imageUrl1;
	private String imageUrl2;
	private String imageUrl3;
	private String imageUrl4;
	
	@OneToOne(fetch=FetchType.LAZY, cascade= CascadeType.ALL,optional=true)
	@JoinColumn(name="location_id")
	private Location cordinates; 
	
	@ElementCollection (fetch=FetchType.EAGER)
	@CollectionTable(name="CLASSIFIED_PARAMS" , joinColumns=@JoinColumn(name="ID"))
	@MapKeyColumn(name="name")
	@Column(name="value")
	private Map<String,String> extraDetails = new HashMap<String, String>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="CLASSIFIED_TAG",
			joinColumns=@JoinColumn(name="id"),
			inverseJoinColumns=@JoinColumn(name="TAG_ID")
			)
	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	private List<Tag> tags;
	
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

	public Location getCordinates() {
		return cordinates;
	}

	public void setCordinates(Location cordinates) {
		this.cordinates = cordinates;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getImageUrl1() {
		return imageUrl1;
	}

	public void setImageUrl1(String imageUrl1) {
		this.imageUrl1 = imageUrl1;
	}

	public String getImageUrl2() {
		return imageUrl2;
	}

	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}

	public String getImageUrl3() {
		return imageUrl3;
	}

	public void setImageUrl3(String imageUrl3) {
		this.imageUrl3 = imageUrl3;
	}

	public String getImageUrl4() {
		return imageUrl4;
	}

	public void setImageUrl4(String imageUrl4) {
		this.imageUrl4 = imageUrl4;
	}

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(String builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public Map<String, String> getExtraDetails() {
		return extraDetails;
	}

	public void setExtraDetails(Map<String, String> extraDetails) {
		this.extraDetails = extraDetails;
	}
	
	public String getParam(String key){
		if (extraDetails != null)
			return extraDetails.get(key);
		return null;
	}

	public void setParam(String key, String value) {
		extraDetails.put(key, value);		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
