package com.fairdeal.action.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.fairdeal.Constants;
import com.fairdeal.bean.ClassifiedBean;
import com.fairdeal.bean.UserBean;
import com.fairdeal.bean.UserQueryBean;
import com.fairdeal.entity.Classified;
import com.fairdeal.entity.UserQuery;
import com.fairdeal.service.ClassifiedService;
import com.fairdeal.service.UserQueryService;
import com.fairdeal.service.UserService;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;
import com.fairdeal.utility.DaoUtils;
import com.fairdeal.utility.SessionUtil;
import com.fairdeal.utility.Util;

@Component(value = "classifiedaction")
@Scope(value = "session")
public class ClassifiedAction implements Serializable{
	
	private String classifiedId;
	
	@ManagedProperty(value="#{classifiedbean}")
	private ClassifiedBean classifiedBean;
	
	private UploadedFile uploadedImage1;
	private UploadedFile uploadedImage2;
	private UploadedFile uploadedImage3;
	private UploadedFile uploadedImage4;
	
	private UserBean userBean;

	@Autowired
	ClassifiedService classifiedService;
	
	@Autowired
	SessionUtil sessionUtil;

	public void register(ClassifiedBean classififed) throws IOException {
		Map<String, InputStream> imagesMap = new HashMap<String, InputStream>();
		imagesMap.put(Constants.Classified.CLASIFIED_IMAGE1, Util.getImageStream(classififed.getUploadedImage1()));
		imagesMap.put(Constants.Classified.CLASIFIED_IMAGE2, Util.getImageStream(classififed.getUploadedImage2()));
		imagesMap.put(Constants.Classified.CLASIFIED_IMAGE3, Util.getImageStream(classififed.getUploadedImage3()));
		imagesMap.put(Constants.Classified.CLASIFIED_IMAGE4, Util.getImageStream(classififed.getUploadedImage4()));
		int classifiedType = Integer.parseInt(classififed.getType());
		long classifiedId = classifiedService.registerClassified(classififed.getShortTitle(), classififed.getDescription(), classififed.getAddress(), classififed.getCity(), classififed.getState(), classififed.getPincode(), classififed.getContactNumber(),classififed.getEmail(), "" + classififed.getPrice(),sessionUtil.getUserbean().getUserId(),classifiedType, imagesMap);

		FacesContext.getCurrentInstance().addMessage("successfullregistration", new FacesMessage("Classified posted successfully to the system:" + classififed.getShortTitle()));
		LoggerUtil.debug("Saving classififed: " + classififed);
		RequestContext.getCurrentInstance().execute("classifiedRegisterSuccessfully()");
		Util.doInternalRedirect("/views/classifiedDetailsPage.xhtml?classifiedid="+classifiedId);
	}

	public void fetchClassified(){
		Classified classifiedEntity = classifiedService.getClassified(classifiedId);
		classifiedBean= DaoUtils.getClassified(classifiedEntity);
		userBean = DaoUtils.getUser(ObjectRepository.getBean(UserService.class).getUser((Long.parseLong(classifiedBean.getAgentId()))));
	}
	
	public void submitClassifiedQuery(ClassifiedBean classified, UserQueryBean userQuery){
		UserBean userbean = sessionUtil.getUserbean();
		if (userbean.getEmailAddress()==null){
			throw new AccessDeniedException("Kindly Login first to send query");
		}
		userQuery.setClassifiedId(classified.getId());
		
		ObjectRepository.getBean(UserQueryService.class).insertUserQuery(userQuery.getFirstName(), userQuery.getLastName(),userQuery.getUserContactNumber()
				,userQuery.getUserEmailAddress(), userQuery.getOptionalMessage(), Long.parseLong(classified.getAgentId()), classified.getId());
		
		LoggerUtil.debug("Trying to submit the query for and classified:"+classified);
		LoggerUtil.debug("User: "+userbean+" is using these details");
	}
	
	public void handleImageUpload1(FileUploadEvent event) throws Exception{
		uploadedImage1 = event.getFile();
		uploadedImage1.write("../temp/"+uploadedImage1.getFileName());
	}

	public void handleImageUpload2(FileUploadEvent event){
		uploadedImage2 = event.getFile();
	}
	
	public void handleImageUpload3(FileUploadEvent event){
		uploadedImage3 = event.getFile();
	}
	
	public void handleImageUpload4(FileUploadEvent event){
		uploadedImage4 = event.getFile();
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

	public String getClassifiedId() {
		return classifiedId;
	}

	public void setClassifiedId(String classifiedId) {
		this.classifiedId = classifiedId;
	}

	public ClassifiedBean getClassifiedBean() {
		return classifiedBean;
	}

	public void setClassifiedBean(ClassifiedBean classifiedBean) {
		this.classifiedBean = classifiedBean;
	}

	public ClassifiedService getClassifiedService() {
		return classifiedService;
	}

	public void setClassifiedService(ClassifiedService classifiedService) {
		this.classifiedService = classifiedService;
	}

	@PreAuthorize(value="hasRole('"+Constants.User.ROLE_USER+"')")
	public void validateRegisteredAgent(){}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
