package com.fairdeal.bean.list;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.fairdeal.Constants;
import com.fairdeal.bean.ClassifiedBean;
import com.fairdeal.bean.UserBean;
import com.fairdeal.entity.Classified;
import com.fairdeal.entity.User;
import com.fairdeal.filter.ClassifiedFilter;
import com.fairdeal.service.ClassifiedService;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;
import com.fairdeal.utility.DaoUtils;
import com.fairdeal.utility.SessionUtil;

@Component(value = "classifiedlist")
@Scope("view")
public class ClassifiedListBean implements Serializable {

	private String targetArea;
	private int listingType;
	private long totalClassifieds;
	private int currentPage;
	private int itemsOnPage;

	private List<ClassifiedBean> classifieds;
	
	public List<ClassifiedBean> getClassifieds() {
		return classifieds;
	}

	public void setClassifieds(List<ClassifiedBean> classifieds) {
		this.classifieds = classifieds;
	}

	@PostConstruct
	public void updatePage() {
		itemsOnPage = Config.getInt(Constants.Config.CLASSIFIED_PAGINATION_SIZE);
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
		LoggerUtil.debug("Ajax is calling to reload the updatePage with currentPage number as " + currentPage);
		totalClassifieds = ObjectRepository.getBean(ClassifiedService.class).getTotalClassifiedCount(targetArea, listingType);
		updatePage((currentPage - 1) * itemsOnPage);
	}

	public void doNothing(){
		//This function does nothing, 
		//But on every ajax reqest updatePage() is called. And that renders the data. 
		//Because updatePage is Called on postconstruct. 
	}
	
	public void updatePage(int firstPage) {
		classifieds = new LinkedList<ClassifiedBean>();
		ClassifiedFilter filter = new ClassifiedFilter();
		filter.setClassifiedType(listingType);
		List<Classified> classifies = ObjectRepository.getBean(ClassifiedService.class).getClassifieds(firstPage, itemsOnPage, targetArea, filter);
		LoggerUtil.debug("This button is used to get the listing which are in the application: " + targetArea);
		for (Classified classisfied : classifies) {
			ClassifiedBean bean = DaoUtils.getClassified(classisfied);
			classifieds.add(bean);
		}
	}

	public String getTargetArea() {
		return targetArea;
	}

	public void setTargetArea(String targetArea) {
		this.targetArea = targetArea;
	}

	public long getTotalClassifieds() {
		return totalClassifieds;
	}

	public void setTotalClassifieds(long totalClassifieds) {
		this.totalClassifieds = totalClassifieds;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getItemsOnPage() {
		return itemsOnPage;
	}

	public void setItemsOnPage(int itemsOnPage) {
		this.itemsOnPage = itemsOnPage;
	}

	@PreAuthorize(value = "hasRole('" + Constants.User.ROLE_USER + "')")
	public void getClassifiedAgent(ClassifiedBean classififed) {
		SessionUtil sessionUtil = ObjectRepository.getBean(SessionUtil.class);
		User user = ObjectRepository.getBean(ClassifiedService.class).getClassifiedAgentDetails(""+classififed.getId(), sessionUtil.getUser().getUserId());

		classififed.setClassifiedAccess(true);
		UserBean userbean = DaoUtils.getUser(user);
		classififed.setUser(userbean);
		LoggerUtil.debug("User is having access to getclassified");
	}

	public void checkLogedId() {
		LoggerUtil.debug("User is having access to getclassified:: checkLogedId");
	}

	public int getListingType() {
		return listingType;
	}

	public void setListingType(int listingType) {
		this.listingType = listingType;
	}
}
