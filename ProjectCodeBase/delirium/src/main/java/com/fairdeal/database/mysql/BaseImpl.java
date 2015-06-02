package com.fairdeal.database.mysql;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairdeal.service.AgentService;
import com.fairdeal.service.ClassifiedService;
import com.fairdeal.service.UserService;
import com.fairdeal.util.ObjectRepository;

@Component(value = "baseimpl")
@Transactional
public class BaseImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public void permanentDelete(Object object) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(object);
	}
	
	public void permanentDelete(String entityName, long objectId) {
		Session session = sessionFactory.getCurrentSession();
		Object o = null;
		if (entityName.equalsIgnoreCase("User")) {
			o = ObjectRepository.getBean(UserService.class).getUser(objectId);
		}
		if (entityName.equalsIgnoreCase("Agent")) {
			o = ObjectRepository.getBean(AgentService.class).getAgent(objectId);
		}
		if (entityName.equalsIgnoreCase("Classified")) {
			o = ObjectRepository.getBean(ClassifiedService.class).getClassified("" + objectId);
		}
		if (o != null)
			session.delete(o);
	}

}
