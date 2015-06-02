package com.fairdeal.bean.list;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fairdeal.bean.AgentBean;
import com.fairdeal.entity.Agent;
import com.fairdeal.service.AgentService;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.utility.DaoUtils;

@Component(value = "agentslist")
@Scope("session")
public class AgentListBean implements Serializable {

	private String test;
	public List<AgentBean> agents;
	
	
	@Autowired
	AgentService agentService;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public void init(){
		LoggerUtil.debug("Calling the doomething method here.");
		agents = new LinkedList<AgentBean>();
		
		List<Agent> agentList = agentService.getAllAgents();
		for (Agent tempAgent : agentList) {
			AgentBean bean = DaoUtils.getAgents(tempAgent);
			agents.add(bean);
		}
	}
	
	public void fetchAgents() {
	}
	
	public void fetchAgents(String agentsId) {
	
		
	}
	
	
	public List<AgentBean> getAgents() {
		return agents;
	}

	public void setAgents(List<AgentBean> agents) {
		this.agents = agents;
	}

	public AgentService getAgentService() {
		return agentService;
	}

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}

}
