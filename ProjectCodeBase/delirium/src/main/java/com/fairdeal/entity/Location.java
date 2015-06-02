package com.fairdeal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOCATION_CORDINATES")
public class Location implements Serializable {
	
	@Id
	@Column(name="location_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private double latitude;
	private double longitude;
	private String description;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + ", description=" + description + "]";
	}

//	public int getClassifiedId() {
//		return classifiedId;
//	}
//
//	public void setClassifiedId(int classifiedId) {
//		this.classifiedId = classifiedId;
//	}

//	public Classified getClassified() {
//		return classified;
//	}
//
//	public void setClassified(Classified classified) {
//		this.classified = classified;
//	}

}
