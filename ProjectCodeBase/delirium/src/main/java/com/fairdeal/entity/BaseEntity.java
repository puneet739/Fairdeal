package com.fairdeal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private Date createdDate;
	private Date updatedDate;
	private Long version;
	private int deleted;
	
	private String extraParam1;
	private String extraParam2;
	private String extraParam3;
	private String extraParam4;
	private String extraParam5;
	private String extraParam6;
	private String extraParam7;
	private String extraParam8;
	
	private int category;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getExtraParam1() {
		return extraParam1;
	}

	public void setExtraParam1(String extraParam1) {
		this.extraParam1 = extraParam1;
	}

	public String getExtraParam2() {
		return extraParam2;
	}

	public void setExtraParam2(String extraParam2) {
		this.extraParam2 = extraParam2;
	}

	public String getExtraParam3() {
		return extraParam3;
	}

	public void setExtraParam3(String extraParam3) {
		this.extraParam3 = extraParam3;
	}

	public String getExtraParam4() {
		return extraParam4;
	}

	public void setExtraParam4(String extraParam4) {
		this.extraParam4 = extraParam4;
	}

	public String getExtraParam5() {
		return extraParam5;
	}

	public void setExtraParam5(String extraParam5) {
		this.extraParam5 = extraParam5;
	}

	public String getExtraParam6() {
		return extraParam6;
	}

	public void setExtraParam6(String extraParam6) {
		this.extraParam6 = extraParam6;
	}

	public String getExtraParam7() {
		return extraParam7;
	}

	public void setExtraParam7(String extraParam7) {
		this.extraParam7 = extraParam7;
	}

	public String getExtraParam8() {
		return extraParam8;
	}

	public void setExtraParam8(String extraParam8) {
		this.extraParam8 = extraParam8;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

}
