package com.fairdeal.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.fairdeal.Results;
import com.fairdeal.dao.UserDao;
import com.fairdeal.entity.User;
import com.fairdeal.service.UserService;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;
import com.fairdeal.util.Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean/application-beans.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class BaseTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	AbstractDataSource datasource;
	
	@Before
	public void setUp(){
		LoggerUtil.debug("Doinin initializaion from BaseTest");
	}
	
	public void deleteElementTable(String tableName){
		sessionFactory.getCurrentSession().createSQLQuery("truncate table "+tableName).executeUpdate();
	}
	
	public void deleteTable(String tableName){
		sessionFactory.getCurrentSession().createQuery("delete from "+tableName).executeUpdate();
	}
	

	private String firstName = "Puneet";
	private String lastName = "Behl";
	private String phoneNumber = "9711616135";
	private String dateOfBirth = "01/12/2014"; 	//The format is DD/MM/yyyy
	private Date dateOfBithDateFormat =Util.getCurrentDate();
	
	public long registerUser(String emailAddress){
		int result = ObjectRepository.getBean(UserService.class).registerUser(firstName, lastName, emailAddress, phoneNumber, dateOfBirth, null);
		User user = ObjectRepository.getBean(UserService.class).getUser(emailAddress);
		user.setClassifiedQuota(10000);
		ObjectRepository.getBean(UserService.class).modifyUser(user);
		return user.getId();
	}
}
