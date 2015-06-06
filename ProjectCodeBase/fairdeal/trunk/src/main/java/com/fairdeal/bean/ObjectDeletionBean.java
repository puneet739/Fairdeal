package com.fairdeal.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component(value = "objectdeletion")
@Scope(value = "request")
public class ObjectDeletionBean {

	public String objectType;
	public String retreviedObject;
	
	public String id;
	public String email;

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getRetreviedObject() {
		return retreviedObject;
	}

	public void setRetreviedObject(String retreviedObject) {
		this.retreviedObject = retreviedObject;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
