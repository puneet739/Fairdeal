package com.fairdeal.bean.list;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.fairdeal.bean.ClassifiedBean;
import com.fairdeal.entity.Classified;
import com.fairdeal.service.ClassifiedService;
import com.fairdeal.utility.DaoUtils;
import com.fairdeal.utility.LoggerUtil;
import com.fairdeal.utility.ObjectRepository;

@ManagedBean(name = "classifiedlist")
@RequestScoped
public class ClassifiedList {

	private String targetArea;

	private ClassifiedService service;

	private List<ClassifiedBean> classifieds;

	public List<ClassifiedBean> getClassifieds() {
		return classifieds;
	}

	public void setClassifieds(List<ClassifiedBean> classifieds) {
		this.classifieds = classifieds;
	}

	public void getListing() {
		classifieds = new LinkedList<ClassifiedBean>();

		LoggerUtil.debug("This button is used to get the listing which are in the application: " + targetArea);
		if (service == null) {
			service = ObjectRepository.getSpringBean(ClassifiedService.class);
		}
		for (Classified classisfied : service.getClassifieds()) {
			classifieds.add(DaoUtils.getClassified(classisfied));
		}
	}

	public ClassifiedService getService() {
		return service;
	}

	public void setService(ClassifiedService service) {
		this.service = service;
	}

	public String getTargetArea() {
		return targetArea;
	}

	public void setTargetArea(String targetArea) {
		this.targetArea = targetArea;
	}
}
