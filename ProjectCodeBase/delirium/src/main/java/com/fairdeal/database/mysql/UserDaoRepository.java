package com.fairdeal.database.mysql;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.dao.UserDao;
import com.fairdeal.entity.User;
import com.fairdeal.util.Util;

public class UserDaoRepository implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer insertUser(User currentUser) {
		Session session = sessionFactory.getCurrentSession();
		currentUser.setCreatedDate(Util.getCurrentDate());
		currentUser.setUpdatedDate(Util.getCurrentDate());
		session.saveOrUpdate(currentUser);
		return null;
	}

	@Override
	public Integer updateUser(User currentUser) {
		Session session = sessionFactory.getCurrentSession();
		currentUser.setUpdatedDate(Util.getCurrentDate());
		session.update(currentUser);
		return null;
	}

	@Override
	public Integer DeleteUser(User currentUser) {
		currentUser.setDeleted(Constants.User.AGENT_DELETED);
		updateUser(currentUser);
		return null;
	}

	@Override
	public Integer DeleteUser(Long userId) {
		User user = getUser(userId);
		DeleteUser(user);
		return null;
	}

	@Override
	public User getUser(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, userId);
		return user;
	}

	@Override
	public User getUser(String emailAddress) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where emailAddress= :email and deleted=:deleted");
		query.setParameter("email", emailAddress);
		query.setParameter("deleted", Constants.User.AGENT_NOT_DELETED);
		User users =  (User) query.uniqueResult();
		return users;
	}

	@Override
	public List<User> getAllUsers() {
		return getUsers(0,0);
	}

	@Override
	public List<User> getUsers(int firstResult, int maxResult) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where deleted=:deleted order by updatedDate desc");
		query.setParameter("deleted", Constants.User.AGENT_NOT_DELETED);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<User> users = query.list();
		return users;
	}

	@Override
	public Long getTotalSize() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from User where deleted=:deleted order by updatedDate desc");
		query.setParameter("deleted", Constants.Classified.CLASIFIED_NOT_DELETED);
		Long  result = (Long) query.uniqueResult();
		return result;
	}

}
