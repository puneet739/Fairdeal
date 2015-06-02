package com.fairdeal.database.mysql;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.dao.AgentDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.util.Util;

public class AgentDaoRepository implements AgentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Long insertAgent(Agent agent) {
		Session session = sessionFactory.getCurrentSession();
		agent.setCreatedDate(Util.getCurrentDate());
		agent.setUpdatedDate(Util.getCurrentDate());
		Long agentId = (Long)session.save(agent);
		return (long)agentId;
	}

	@Override
	public Long updateAgent(Agent agent) {
		Session session = sessionFactory.getCurrentSession();
		agent.setUpdatedDate(Util.getCurrentDate());
		session.update(agent);
		return null;
	}

	@Override
	public Long deleteAgent(Agent agent) {
		agent.setDeleted(Constants.Agent.AGENT_DELETED);
		return updateAgent(agent);
	}

	@Override
	public Long deleteAgent(long agentId) {
		Agent agent  = getAgent(agentId);
		deleteAgent(agent);
		return null;
	}

	@Override
	public Agent getAgent(long agentId) {
		Session session = sessionFactory.getCurrentSession();
		Agent agent = (Agent) session.get(Agent.class, agentId);
		return agent;
	}

	
	@Override
	public List<Agent> getAllAgents() {
		return getAgents(0, 0);
	}

	@Override
	public List<Agent> getAgents(int firstResult, int maxResult) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Agent where deleted=:deleted order by updatedDate desc");
		query.setParameter("deleted", Constants.Agent.AGENT_NOT_DELETED);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<Agent> agents = query.list();
		return agents;
	}

	@Override
	public Long getTotalSize() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Agent where deleted=:deleted order by updatedDate desc");
		query.setParameter("deleted", Constants.Classified.CLASIFIED_NOT_DELETED);
		Long  result = (Long) query.uniqueResult();
		return result;
	}

}
