package com.fairdeal.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.Results;
import com.fairdeal.dao.AgentDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.entity.User;
import com.fairdeal.service.AgentService;
import com.fairdeal.service.UserService;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;
import com.fairdeal.util.Util;

public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentDao agentDao;

	@Override
	@Transactional
	public List<Agent> getAllAgents() {
		LoggerUtil.debug("Fetching agents using agent Service");
		return agentDao.getAllAgents();
	}

	public AgentDao getAgentDao() {
		return agentDao;
	}

	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}

	@Override
	@Transactional
	public int registerAgent(String firstName, String lastName, String address, String phoneNumber, String emailAddress, String agentDescription, String website, Map<String, InputStream> imagesMap) {
		try {
			UserService service = ObjectRepository.getBean(UserService.class);
			service.registerUser(firstName, lastName, emailAddress, phoneNumber, "", phoneNumber);
			Agent agent = generateAgentEntity(null, firstName, lastName, address, phoneNumber, emailAddress, agentDescription, website);
			agentDao.insertAgent(agent);
			User user = service.getUser(emailAddress);
			user.setAgentId(agent.getId());
			user.setAgentId(50);
			service.modifyUser(user);
			uploadAgentImages(agent, imagesMap);
		} catch (ConstraintViolationException e) {
			return Results.AGENT.EMAIL_ALREADY_REGISTERED;
		}
		return Results.SUCCESS;
	}

	@Override
	@Transactional
	public int modifyAgent(long agentId, String firstName, String lastName, String address, String phoneNumber, String emailAddress, String agentDescription, String website, Map<String, InputStream> imagesMap) {
		try {
			Agent agent = agentDao.getAgent(agentId);
			generateAgentEntity(agent, firstName, lastName, address, phoneNumber, emailAddress, agentDescription, website);
			agentDao.updateAgent(agent);
			uploadAgentImages(agent, imagesMap);
		} catch (ConstraintViolationException e) {
			return Results.AGENT.EMAIL_ALREADY_REGISTERED;
		}
		return Results.SUCCESS;
	}

	private void uploadAgentImages(Agent agent, Map<String, InputStream> imagesMap) {
		if (imagesMap == null)
			return;
		String agentImage = Util.saveResizedImageFile(imagesMap.get(Constants.Agent.AGENT_MAIN_IMAGE), Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), agent.getId() + "_" + Constants.Agent.AGENT_MAIN_IMAGE + "."+Constants.IMAGE_FORMAT);
		String imageUrl1 = Util.saveResizedImageFile(imagesMap.get(Constants.Agent.AGENT_IMAGE1), Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), agent.getId() + "_" + Constants.Agent.AGENT_IMAGE1 + "."+Constants.IMAGE_FORMAT);
		String imageUrl2 = Util.saveResizedImageFile(imagesMap.get(Constants.Agent.AGENT_IMAGE2), Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), agent.getId() + "_" + Constants.Agent.AGENT_IMAGE2 + "."+Constants.IMAGE_FORMAT);
		String imageUrl3 = Util.saveResizedImageFile(imagesMap.get(Constants.Agent.AGENT_IMAGE3), Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), agent.getId() + "_" + Constants.Agent.AGENT_IMAGE3 + "."+Constants.IMAGE_FORMAT);
		String imageUrl4 = Util.saveResizedImageFile(imagesMap.get(Constants.Agent.AGENT_IMAGE4), Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), agent.getId() + "_" + Constants.Agent.AGENT_IMAGE4 + "."+Constants.IMAGE_FORMAT);
		if (agentImage != null)
			agent.setAgentImage(agentImage);
		if (imageUrl1 != null)
			agent.setImageUrl1(imageUrl1);
		if (imageUrl2 != null)
			agent.setImageUrl2(imageUrl2);
		if (imageUrl3 != null)
			agent.setImageUrl3(imageUrl3);
		if (imageUrl4 != null)
			agent.setImageUrl4(imageUrl4);
		agentDao.updateAgent(agent);
	}

	public Agent generateAgentEntity(Agent agent, String firstName, String lastName, String address, String phoneNumber, String emailAddress, String agentDescription, String website) {
		if (agent == null)
			agent = new Agent();
		agent.setFirstName(firstName);
		agent.setLastName(lastName);
		agent.setAddress(address);
		agent.setPhoneNumber(phoneNumber);
		agent.setEmailAddress(emailAddress);
		agent.setAgentDescription(agentDescription);
		agent.setWebsite(website);

		return agent;
	}

	@Override
	public Agent getAgent(long agentId) {
		Agent agent = agentDao.getAgent(agentId);
		return agent;
	}

	@Override
	public Agent getAgent(String emailaddress) {
		// TODO Auto-generated method stub
		return null;
	}
}
