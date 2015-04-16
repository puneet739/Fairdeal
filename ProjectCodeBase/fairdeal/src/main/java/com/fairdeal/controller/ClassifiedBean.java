package com.fairdeal.controller;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ComponentSystemEvent;

import com.fairdeal.utility.LoggerUtil;

@ManagedBean(name="classifiedbean")
@RequestScoped
public class ClassifiedBean implements Serializable{

	public List<String> test;
	public List<String> postConst;
	
	public void loadClassified(ComponentSystemEvent event){
		LoggerUtil.debug("Calling loadClassified before applicaiton start");
		test = new LinkedList<String>();
		for (int i=0; i<100;i++){
			test.add("Test from "+i);
		}
	}
	
	@PostConstruct
	public void doFunction(){
		LoggerUtil.debug("We are calling this method post construction of the JSF");
		postConst = new LinkedList<String>();
		for (int i=0; i<100;i++){
			postConst.add("Test from post construct "+i);
		}
	}

	public List<String> getTest() {
		return test;
	}

	public void setTest(List<String> test) {
		this.test = test;
	}

	public List<String> getPostConst() {
		return postConst;
	}

	public void setPostConst(List<String> postConst) {
		this.postConst = postConst;
	}
}
