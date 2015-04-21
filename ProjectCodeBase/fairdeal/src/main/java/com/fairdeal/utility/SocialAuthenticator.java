package com.fairdeal.utility;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.brickred.socialauth.cdi.SocialAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.fairdeal.entity.User;

/**
 * This is the main component class, it is referred in application pages and
 * provides the navigation functionality to the user.
 * 
 * @author lakhdeeps@brickred.com
 * 
 */

@SessionScoped
@Component
@Controller(value="socialauthenticator")
public class SocialAuthenticator implements Serializable {

	private static final Logger log = Logger.getLogger(SocialAuthenticator.class);

	@Autowired
	private SocialAuth socialauth ;
	
	@Autowired
	private User user;
	

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
		socialauth.setViewUrl("/views/loginSuccess.xhtml");

		//if (btnClicked.indexOf("facebook") != -1) {
			socialauth.setId("facebook");
			log.info("***facebook*********" + socialauth.getId());
//		} else if (btnClicked.indexOf("twitter") != -1) {
//			socialauth.setId("twitter");
//			log.info("***twitter*********" + socialauth.getId());
//		} else if (btnClicked.indexOf("yahoo") != -1) {
//			socialauth.setId("yahoo");
//			log.info("***yahoo*********" + socialauth.getId());
//		} else if (btnClicked.indexOf("hotmail") != -1) {
//			socialauth.setId("hotmail");
//			log.info("***hotmail*********" + socialauth.getId());
//		} else if (btnClicked.indexOf("google") != -1) {
//			socialauth.setId("google");
//			log.info("***google*********" + socialauth.getId());
//		} else if (btnClicked.indexOf("linkedin") != -1) {
//			socialauth.setId("linkedin");
//			log.info("***linkedin*********" + socialauth.getId());
//		} else if (btnClicked.indexOf("foursquare") != -1) {
//			socialauth.setId("foursquare");
//			log.info("***foursquare*********" + socialauth.getId());
//		} else {
//			socialauth.setId(openID);
//			log.info("***openID*********" + socialauth.getId());
//		}
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
				user.setFirstName(socialauth.getProfile().getFirstName());
				user.setEmailAddress((socialauth.getProfile().getEmail()));
			} catch (Exception e) {
				log.warn(e);
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