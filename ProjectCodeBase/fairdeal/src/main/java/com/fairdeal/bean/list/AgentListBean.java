package com.fairdeal.bean.list;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairdeal.dao.AgentDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.service.AgentService;
import com.fairdeal.utility.DaoUtils;
import com.fairdeal.utility.LoggerUtil;
import com.fairdeal.utility.ObjectRepository;

@Component(value = "agentslist")
@SessionScoped
public class AgentListBean implements Serializable {

	private String test;
	public List<AgentDao> agents;

	@Autowired
	private AgentService service;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@PostConstruct
	public void fetchAgents() {
		LoggerUtil.debug("Calling the doomething method here.");
		agents = new LinkedList<AgentDao>();
		//service = ObjectRepository.getSpringBean(AgentService.class);
		List<Agent> agentList = service.getAgents();
		for (Agent tempAgent : agentList) {
			AgentDao bean = DaoUtils.getAgents(tempAgent);
			agents.add(bean);
		}
	}

	public List<AgentDao> getAgents() {
		return agents;
	}

	public void setAgents(List<AgentDao> agents) {
		this.agents = agents;
	}

	public AgentService getService() {
		return service;
	}

	public void setService(AgentService service) {
		this.service = service;
	}

}
