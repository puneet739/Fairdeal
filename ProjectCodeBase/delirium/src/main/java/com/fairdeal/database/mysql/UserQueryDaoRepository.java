package com.fairdeal.database.mysql;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.dao.UserQueryDao;
import com.fairdeal.entity.UserQuery;
import com.fairdeal.util.Util;

public class UserQueryDaoRepository implements UserQueryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertUserQuery(UserQuery query) {
		Session session = sessionFactory.getCurrentSession();
		query.setCreatedDate(Util.getCurrentDate());
		query.setUpdatedDate(Util.getCurrentDate());
		session.save(query);
	}

	@Override
	public void modifyUserQuery(UserQuery query) {
		Session session = sessionFactory.getCurrentSession();
		query.setUpdatedDate(Util.getCurrentDate());
		session.save(query);
	}

	@Override
	public void deleteUserQuery(UserQuery query) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(query);
	}

	@Override
	public UserQuery getQuery(long queryId) {
		Session session = sessionFactory.getCurrentSession();
		UserQuery query = (UserQuery) session.get(UserQuery.class, queryId);
		return query;
	}
	
	@Override
	public List<UserQuery> getUserQueries(String sqlQuery) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sqlQuery);
		List<UserQuery> list = query.list();
		return list;
	}

}
