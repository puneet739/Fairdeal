package com.fairdeal.utility;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fairdeal.Constants;
import com.fairdeal.bean.UserBean;
import com.fairdeal.entity.User;
import com.fairdeal.util.LoggerUtil;

@Component(value="sessionutil")
@Scope("session")
public class SessionUtil implements Serializable{

	@Autowired
	UserBean user;

	public void serUserDetails(User userEntity) {
		user.setUserId(""+userEntity.getId());
		user.setEmailAddress(userEntity.getEmailAddress());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		LoggerUtil.debug("User Roles are"+userEntity.getUserRoles());
		for (String roles: userEntity.getUserRoles()){
			user.getRoles().add(roles);
		}
		user.setPhoneNumber(userEntity.getPhoneNumber());
	}
	
	public void logout(){
		user= new UserBean();
	}

	public UserBean getUserbean() {
		return user;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
	RestTemplate restTemplate = new RestTemplate();
	
	public void validateReCaptcha(ActionEvent event){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		String secret = Constants.AppConfig.RE_CAPTCHA_KEY;
		String gResponse = context.getRequestParameterMap().get(Constants.AppConfig.RE_CAPTCHA_RESPONSE);
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		if(secret==null || gResponse==null){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Captcha fails, Please confirm you are human", "Captcha fails,Please confirm you are human");
			FacesContext.getCurrentInstance().addMessage("captchaError", msg);
			throw new ValidatorException(msg);
		}
		CaptchaReponse response = restTemplate.getForObject(Constants.AppConfig.RE_CAPTCHA_URL+"?secret=" + secret + "&response=" + gResponse, CaptchaReponse.class);
		System.out.println(response.getSuccess());
		if (response.getSuccess().equals("true")) {
			return;
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Captcha fails, Please confirm you are human", "Captcha fails,Please confirm you are human");
			FacesContext.getCurrentInstance().addMessage("captchaError", msg);
			throw new ValidatorException(msg);
		}
	}
}

class CaptchaReponse {

	private String success;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
}

