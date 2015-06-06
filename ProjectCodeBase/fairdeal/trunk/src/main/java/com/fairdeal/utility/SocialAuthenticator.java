package com.fairdeal.utility;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import org.apache.log4j.Logger;
import org.brickred.socialauth.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.fairdeal.Constants;
import com.fairdeal.Results;
import com.fairdeal.bean.SocialAuth;
import com.fairdeal.bean.UserBean;
import com.fairdeal.entity.User;
import com.fairdeal.service.UserService;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;
import com.fairdeal.util.Util;

/**
 * This is the main component class, it is referred in application pages and
 * provides the navigation functionality to the user.
 * 
 * @author lakhdeeps@brickred.com
 * 
 */

@Scope("session")
@Component
@Controller(value = "socialauthenticator")
public class SocialAuthenticator implements Serializable {

	private static final Logger log = Logger.getLogger(SocialAuthenticator.class);

	@Autowired
	private SocialAuth socialauth;

	@Autowired
	private UserBean user;

	@Autowired
	private UserService service;

	@Autowired
	SessionUtil sessionUtil;

	/**
	 * Variable for storing open id from main form
	 */
	private String openID;

	/**
	 * Track the user interaction with main page and set the state of components
	 * accordingly.
	 * 
	 * @param ActionEvent
	 */

	public void updateId(ActionEvent ae) {
		String btnClicked = ae.getComponent().getId();
		log.info("Test Log");
		log.info("*************login method called ************" + socialauth.getId());

		ExternalContext context = javax.faces.context.FacesContext.getCurrentInstance().getExternalContext();

		String viewUrl = context.getInitParameter("successUrl");
		Map<String, String> map = context.getRequestParameterMap();
		String urlString = map.get("urlString");
		String query = map.get("queryString");

		socialauth.setViewUrl("/views/loginSuccess.xhtml");

		socialauth.setId("facebook");
		log.info("***facebook*********" + socialauth.getId());
	}

	/**
	 * Redirect the user back to the main page from success view.
	 * 
	 * @param ActionEvent
	 */
	public String mainPage() {
		return "/home.xhtml";
	}

	public String getUserName() {
		return socialauth.getProfile().getFirstName();
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public void verify(ComponentSystemEvent cse) {

		boolean ajaxRequest = javax.faces.context.FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest();
		if (!ajaxRequest) {
			try {
				socialauth.connect();
				Profile profile = socialauth.getProfile();
				User userEntity = service.getUser(profile.getEmail());

				if (userEntity == null) {
					String dob = "01/02/2015";
					if (profile.getDob() != null)
						dob = profile.getDob().toString();
					int result = service.registerUser(profile.getFirstName(), profile.getLastName(), profile.getEmail(), null, dob, "FBxxxxxx");
					if (result == Results.SUCCESS)
						userEntity = service.getUser(profile.getEmail());
						service.modifyUser(userEntity);
				}
				AuthenticationManager authmanager = ObjectRepository.getContext().getBean("authenticationManager", AuthenticationManager.class);
				Authentication request = new UsernamePasswordAuthenticationToken(userEntity.getEmailAddress(), userEntity.getPassword());
				Authentication result = authmanager.authenticate(request);
				SecurityContextHolder.getContext().setAuthentication(result);
				sessionUtil.serUserDetails(userEntity);
			} catch (Exception e) {
				LoggerUtil.error("Exception is caused while FB login", e);
			}
		}
	}

	public SocialAuth getSocialauth() {
		return socialauth;
	}

	public void setSocialauth(SocialAuth socialauth) {
		this.socialauth = socialauth;
	}

}