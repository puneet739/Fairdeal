package com.fairdeal.utility;

import com.fairdeal.bean.ClassifiedBean;
import com.fairdeal.dao.AgentDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.entity.Classified;

public class DaoUtils {

	public static ClassifiedBean getClassified(Classified entity){
		ClassifiedBean bean = new ClassifiedBean();
		bean.setId(entity.getId());
		bean.setAgentId(entity.getAgentId());
		bean.setDescription(entity.getDescription());
		bean.setLatitude(entity.getCordinates().getLatitude());
		bean.setLongitude(entity.getCordinates().getLongitude());
		return bean;
	}

	public static AgentDao getAgents(Agent tempAgent) {
		AgentDao bean = new AgentDao();
		bean.setName(tempAgent.getName());
		bean.setImageUrl(tempAgent.getImageUrl());
		bean.setId(tempAgent.getAgentId());
		return bean;
	}
}
