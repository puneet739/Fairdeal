package com.fairdeal.action.bean;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fairdeal.Constants;
import com.fairdeal.bean.AgentBean;
import com.fairdeal.entity.Agent;
import com.fairdeal.service.AgentService;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.utility.DaoUtils;
import com.fairdeal.utility.Util;

@Component(value = "agentaction")
@Scope(value = "session")
public class AgentAction implements Serializable{

	@Autowired
	AgentBean agent;
	
	@Autowired
	private AgentService service;

	public void doSubmit(AgentBean agent) {
		Map<String, InputStream> imagesMap= getAgentImageMap();
		
		service.registerAgent(agent.getFirstname(), agent.getLastname(),agent.getAddress(),agent.getContactNumber(),agent.getEmailId(),agent.getDescription(),
				agent.getWebsite(),imagesMap);
		FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("User Registered Succesfully with Agent Email:" + agent.getEmailId()));
		LoggerUtil.debug("Submiting the Agent Bean: " + this);
	}
	
	private Map<String, InputStream> getAgentImageMap(){
		Map<String, InputStream> imagesMap= new HashMap<String, InputStream>();
		imagesMap.put(Constants.Agent.AGENT_MAIN_IMAGE, Util.getImageStream(this.agent.getUploadedMainImage()));
		return imagesMap;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		LoggerUtil.debug("Uploading the image to mail file");
		agent.setUploadedMainImage(event.getFile());
	}

	
	public void fetchAgent(long agentId) {
		int superAgentId = Config.getInt(Constants.Config.AGENT_SUPERAGENT_ID);
		if (agentId==0){
			agentId=superAgentId;
		}
		LoggerUtil.debug("Now trying to fetch the agent with agentId"+agentId);
		Agent agententity = service.getAgent(agentId);
		if (agententity==null){
			LoggerUtil.debug("Agent not found with agentId:"+agentId);
			agententity = service.getAgent(superAgentId);
		}
		agent =  DaoUtils.getAgents(agententity);
		LoggerUtil.debug("Agent received from the backend"+agent);
	}
	
	public void modifyAgent(AgentBean agent){
		service.modifyAgent(agent.getId(), agent.getFirstname(), agent.getLastname(),agent.getAddress(),agent.getContactNumber(),agent.getEmailId(),agent.getDescription(),agent.getWebsite()
			,getAgentImageMap());
		FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("User Modified Succesfully with Agent Email:"+agent.getEmailId()));
	}
	
	public void init(){
		agent= new AgentBean();
	}
	
	public AgentBean getAgent() {
		return agent;
	}

	public void setAgent(AgentBean agent) {
		this.agent = agent;
	}

}
