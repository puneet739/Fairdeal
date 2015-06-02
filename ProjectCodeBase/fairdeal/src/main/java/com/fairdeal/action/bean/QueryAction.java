package com.fairdeal.action.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fairdeal.bean.UserQueryBean;
import com.fairdeal.service.UserQueryService;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;

@Component(value="queryaction")
@Scope("session")
public class QueryAction implements Serializable{

	public void doSubmit(UserQueryBean query) throws IOException{
		UserQueryService service = ObjectRepository.getBean(UserQueryService.class);
		LoggerUtil.debug("Details provied:: "+query.getUserEmailAddress());
		LoggerUtil.debug("Details provied:: "+query.getOptionalMessage());
		LoggerUtil.debug("Details provied:: "+query.getUserContactNumber());
		service.insertUserQuery(query.getFirstName(), query.getLastName(), query.getUserContactNumber(), query.getUserEmailAddress(), query.getOptionalMessage(),query.getAgentId(), 0);
		FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage("Thank you for contacting us, We will get back to you shortly"));
	}

}
