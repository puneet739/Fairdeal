package com.fairdeal.utility;

import java.io.Serializable;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;

@Component(value="loader")
public class Loader implements ApplicationListener<ContextRefreshedEvent>, Serializable{


	private String mainUserid;
	private String applicationName;
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LoggerUtil.debug("This funciton is called only after when all beans are initalized");
		initializeSetup();
	}
	
	public void initializeSetup(){
		mainUserid=Config.getString(com.fairdeal.Constants.Config.AGENT_SUPERAGENT_ID);
		applicationName = Config.getString(com.fairdeal.Constants.Config.APPLICATION_NAME);
	}

	public String getMainUserid() {
		return mainUserid;
	}

	public void setMainUserid(String mainUserid) {
		this.mainUserid = mainUserid;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
