package com.fairdeal.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="TAGS")
public class Tag implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TAG_ID")
	private int tagId;
	
	@Column(unique=true)
	private String name;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="tags")
	private List<Classified> allClassifieds;

	public Tag(String tag){
		this.name=tag;
	}
	
	public Tag(){}
	
	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Classified> getAllClassifieds() {
		return allClassifieds;
	}

	public void setAllClassifieds(List<Classified> allClassifieds) {
		this.allClassifieds = allClassifieds;
	}


	

	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + tagId;
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
		Tag other = (Tag) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tagId != other.tagId)
			return false;
		return true;
	}
}
