package com.fairdeal.utility;

import com.fairdeal.bean.AgentBean;
import com.fairdeal.bean.ClassifiedBean;
import com.fairdeal.bean.UserBean;
import com.fairdeal.entity.Agent;
import com.fairdeal.entity.Classified;
import com.fairdeal.entity.User;

public class DaoUtils {

	public static ClassifiedBean getClassified(Classified entity){
		if (entity==null) return null;
		ClassifiedBean bean = new ClassifiedBean();
		bean.setId(entity.getId());
		bean.setAgentId(entity.getUserId());
		bean.setShortTitle(entity.getShorttitle());
		bean.setAddress(entity.getAddress());
		bean.setCity(entity.getCity());
		bean.setState(entity.getState());
		bean.setContactNumber(entity.getContactNumber());
		bean.setCreatedDate(entity.getCreatedDate());
		bean.setEmail(entity.getEmail());
		bean.setDescription(entity.getDescription());
		bean.setPrice(entity.getPrice());
		bean.setCreatedDate(entity.getCreatedDate());
		bean.setParams(entity.getExtraDetails());
		bean.setCategory(entity.getCategory());
		if (entity.getCordinates() !=null ){
			bean.setLatitude(entity.getCordinates().getLatitude());
			bean.setLongitude(entity.getCordinates().getLongitude());
		}
		bean.setImage1Url(entity.getImageUrl1());
		bean.setImage2Url(entity.getImageUrl2());
		bean.setImage3Url(entity.getImageUrl3());
		bean.setImage4Url(entity.getImageUrl4());
		
		return bean;
	}

	public static AgentBean getAgents(Agent tempAgent) {
		if (tempAgent==null) return null;
		AgentBean agentbean = new AgentBean();
		agentbean.setId(tempAgent.getId());
		agentbean.setFirstname(tempAgent.getFirstName());
		agentbean.setLastname(tempAgent.getLastName());
		agentbean.setAddress(tempAgent.getAddress());
		agentbean.setContactNumber(tempAgent.getPhoneNumber());
		agentbean.setEmailId(tempAgent.getEmailAddress());
		agentbean.setDescription(tempAgent.getAgentDescription());
		agentbean.setImageUrl(tempAgent.getImageUrl());
		agentbean.setMainImageUrl(tempAgent.getAgentImage());
		return agentbean;
	}

	public static UserBean getUser(User user) {
		if (user==null) return null;
		UserBean bean = new UserBean();
		bean.setFirstName(user.getFirstName());
		bean.setLastName(user.getLastName());
		bean.setPhoneNumber(user.getPhoneNumber());
		bean.setImageUrl(user.getImageUrl());
		bean.setEmailAddress(user.getEmailAddress());
		return bean;
	}

	

}
