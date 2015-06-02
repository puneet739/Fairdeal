package com.fairdeal.test.servicetest;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Results;
import com.fairdeal.dao.AgentDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.entity.User;
import com.fairdeal.service.AgentService;
import com.fairdeal.service.UserService;
import com.fairdeal.test.BaseTest;

public class AgentsServiceTest extends BaseTest {

	@Autowired
	AgentService agentservice;
	
	@Autowired
	AgentDao agentdao;
	
	@Autowired
	UserService userservice;
	
	
	public String firstName = "TestFirstName";
	public String lastName = "TestLastName";
	public String desc = "test Desc";
	public String email = "testEmail";
	public String address = "test Address";
	public String phoneNumber = "9711616135";
	public String website = "www.test.com";
	public String emailAddress = "test@test.com";
	
	public InputStream mainImage = null;
	public InputStream image1 = null;
	public InputStream image2 = null;
	
	@Override
	public void setUp() {
		super.setUp();
		deleteTable("Agent");
		deleteElementTable("USERROLES");
		deleteTable("User");
	}
	
	@Test
	public void testregisterAgent(){	
		int result = agentservice.registerAgent(firstName, lastName, address, phoneNumber, emailAddress, desc, website,null);
		assertEquals(Results.SUCCESS, result);
		assertEquals(1,agentdao.getAllAgents().size());
	}
	
	@Test
	public void testregisterAgentCheckingUserParallelly(){	
		int result = agentservice.registerAgent(firstName, lastName, address, phoneNumber, emailAddress, desc, website,null);
		assertEquals(Results.SUCCESS, result);
		assertEquals(1,agentdao.getAllAgents().size());
		Agent agent  = agentdao.getAllAgents().get(0);
		User user = userservice.getUser(emailAddress);
		assertEquals(agent.getId(), user.getAgentId());
	}
	
	
	//This test is expected to fail. 
	//When there is an excpetion in Transaction even after catching, the session is flushed. 
	@Test
	public void registerAgentAlreadyRegistered(){
		int result = agentservice.registerAgent(firstName, lastName, address, phoneNumber, emailAddress, desc, website,null);
		assertEquals(Results.SUCCESS, result);
		assertEquals(1,agentdao.getAllAgents().size());
		
		int result2 = agentservice.registerAgent(firstName, lastName, address, phoneNumber, emailAddress, desc, website, null);
		assertEquals(Results.AGENT.EMAIL_ALREADY_REGISTERED, result2);
	}
	
	@Test
	public void registerEmaileverythingSameDifferentEmail(){
		int result = agentservice.registerAgent(firstName, lastName, address, phoneNumber, emailAddress, desc, website,null);
		assertEquals(Results.SUCCESS, result);
		assertEquals(1,agentdao.getAllAgents().size());
		
		int result2 = agentservice.registerAgent(firstName, lastName, address, phoneNumber, "DifferenEmail@email.com", desc, website,null);
		assertEquals(Results.SUCCESS, result2);
	}
	
	@Test
	public void registerAgentAndModify(){
		int result = agentservice.registerAgent(firstName, lastName, address, phoneNumber, emailAddress, desc, website,null);
		assertEquals(Results.SUCCESS, result);
		assertEquals(1,agentdao.getAllAgents().size());
		List<Agent> allAgents = agentdao.getAllAgents();
		assertEquals(1,allAgents.size());
		Agent insertedAgent = allAgents.get(0);
		assertEquals(firstName, insertedAgent.getFirstName());
		
		int result2 = agentservice.modifyAgent(insertedAgent.getId(),"NEWNAME", lastName, address, phoneNumber, "DifferenEmail@email.com", desc, website,null);
		allAgents = agentdao.getAllAgents();
		assertEquals(1,allAgents.size());
		insertedAgent = allAgents.get(0);
		assertEquals("NEWNAME", insertedAgent.getFirstName());
	}
	
	
}
