package com.fairdeal.aspect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.fairdeal.Constants;
import com.fairdeal.entity.Agent;
import com.fairdeal.entity.Classified;
import com.fairdeal.entity.User;
import com.fairdeal.entity.UserQuery;
import com.fairdeal.notify.email.CustomMailSender;
import com.fairdeal.service.AgentService;
import com.fairdeal.service.UserService;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.Util;

@Aspect
public class EmailAspect {
	
	@Autowired
	CustomMailSender mailSender;
	
	@Autowired
	AgentService agentService;
	
	@Async
	@AfterReturning(pointcut="execution(* com.fairdeal.dao.UserDao.insertUser(..)) && args(user)")
	public void sendEmailOnRegistration(JoinPoint joinpoint, User user) {
		LoggerUtil.debug("This method is called whenever a user is tried to register");
		Map<String, Object> objectMap= new HashMap<String, Object>();
		objectMap.put("user", user);
		mailSender.sendEmail(Constants.EmailTemplates.REGISTER_TEMPLATE, user.getEmailAddress(), Constants.EmailTemplates.REGISTER_SUBJECT
				, objectMap);
	}
	
	@Async
	@AfterReturning(pointcut="execution(* com.fairdeal.util.Util.classifiedDetailsFetch(..)) && args(classified,loggedInUser)")
	public void sendEmaiUserViewClassified(JoinPoint joinpoint, Classified classified, User loggedInUser){
		LoggerUtil.debug("Now sending the email to Admin about the user who tried to check the classified");
		Map<String, Object> objectMap= new HashMap<String, Object>();
		objectMap.put("classified", classified);
		objectMap.put("user", loggedInUser);
		mailSender.sendEmail(Constants.EmailTemplates.VIEWCLASSIFIED_TEMPLATE, Util.getAdminUsers(),null, "User fetched the details of classified"
				, objectMap);
	}
	
	@Async
	@AfterReturning(pointcut="execution(* com.fairdeal.dao.UserQueryDao.insertUserQuery(..)) && args(query)")
	public void sendEmailcontactAgent(JoinPoint joinpoint, UserQuery query){
		LoggerUtil.debug("Now sending the email to Admin about the user who tried to check the classified");
		Map<String, Object> objectMap= new HashMap<String, Object>();
		objectMap.put("query", query);
		Agent agent = agentService.getAgent(query.getAgentId());
		if (agent!=null){
			mailSender.sendEmail(Constants.EmailTemplates.USER_QUERY_TEMPLATE, agent.getEmailAddress(),null, "User Query for Agent"
					, objectMap);
		}
		mailSender.sendEmail(Constants.EmailTemplates.USER_QUERY_TEMPLATE, Util.getAdminUsers(),null, "User Query"
				, objectMap);
	}
	
}
