package com.fairdeal.bean.list;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.fairdeal.dao.AgentDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.service.AgentService;
import com.fairdeal.utility.DaoUtils;
import com.fairdeal.utility.LoggerUtil;
import com.fairdeal.utility.ObjectRepository;

@ManagedBean(name = "agentslist")
@RequestScoped
public class AgentListBean implements Serializable {

	private String test;
	public List<AgentDao> agents;
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
		service = ObjectRepository.getSpringBean(AgentService.class);
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

}
