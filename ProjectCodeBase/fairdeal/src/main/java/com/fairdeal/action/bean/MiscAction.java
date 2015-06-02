package com.fairdeal.action.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fairdeal.bean.ObjectDeletionBean;
import com.fairdeal.database.mysql.BaseImpl;
import com.fairdeal.util.ObjectRepository;

@Component(value = "miscaction")
@Scope(value = "session")
public class MiscAction {

	public void delete(String objectType, String id) {
	}

	public void retrieve(ObjectDeletionBean deletionBean) {
		//String id = deletionBean.getId();
		//String entityType = getObject(deletionBean.getObjectType(), deletionBean.getId());
		//ObjectRepository.getBean(BaseImpl.class).permanentDelete(entityType , Long.parseLong(id));
	}

	public void delete(ObjectDeletionBean deletionBean) {
		String id = deletionBean.getId();
		String entityType = getObject(deletionBean.getObjectType(), deletionBean.getId());
		ObjectRepository.getBean(BaseImpl.class).permanentDelete(entityType , Long.parseLong(id));
	}
	
	private String getObject(String type, String id){
		String result=null;
		if (type.equalsIgnoreCase("1")) {
			result= "User";
		}
		if (type.equalsIgnoreCase("2")) {
			result= "Agent";
		}
		if (type.equalsIgnoreCase("3")) {
			result= "Classified";
		}
		return result;
	}

}
