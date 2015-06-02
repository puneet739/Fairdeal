package com.fairdeal.dao;

import java.util.List;

import com.fairdeal.entity.Agent;

public interface AgentDao extends BaseDao{
	public Long insertAgent(Agent agent);

	public Long updateAgent(Agent agent);

	public Long deleteAgent(Agent agent);

	public Long deleteAgent(long agentId);
	
	public Agent getAgent(long agentId);
	
	public List<Agent> getAllAgents();
	
	public List<Agent> getAgents(int firstResult, int maxResult);
}
