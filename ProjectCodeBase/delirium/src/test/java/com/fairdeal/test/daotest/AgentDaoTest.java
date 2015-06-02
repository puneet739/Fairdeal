package com.fairdeal.test.daotest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.dao.AgentDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.test.BaseTest;

public class AgentDaoTest extends BaseTest{

	@Autowired
	AgentDao agentDao;
	
	@Before
	public void setUp(){
		super.setUp();
		deleteTable("Agent");
	}
	
	@Test
	public void testInsertAgent(){
		Agent agent = new Agent();
		agent.setEmailAddress("PuneetAgent@gmail.com");
		agent.setFirstName("Puneet Behl");
		agentDao.insertAgent(agent);
		
		long agentSize =  agentDao.getTotalSize();
		assertEquals(1, agentSize);
		
		int totalSize = agentDao.getAllAgents().size();
		assertEquals(1, 1);
	}
	
	@Test
	public void testInsertAgentAndDelete(){
		Agent agent = new Agent();
		agent.setEmailAddress("PuneetAgent@gmail.com");
		agent.setFirstName("Puneet Behl");
		agentDao.insertAgent(agent);
		
		long agentSize =  agentDao.getTotalSize();
		assertEquals(1, agentSize);
		
		int totalSize = agentDao.getAllAgents().size();
		assertEquals(1, totalSize);
		
		agentDao.deleteAgent(agentDao.getAllAgents().get(0));
		
		int totalSize2 = agentDao.getAllAgents().size();
		assertEquals(0, totalSize2);
	}
}
