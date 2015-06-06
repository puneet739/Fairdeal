package com.fairdeal.database.mysql;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.dao.ClassifiedDao;
import com.fairdeal.entity.Classified;
import com.fairdeal.filter.ClassifiedFilter;
import com.fairdeal.util.Util;

public class ClassifiedDaoRepository implements ClassifiedDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long insertClassified(Classified classified) {
		Session session = sessionFactory.getCurrentSession();
		classified.setCreatedDate(Util.getCurrentDate());
		classified.setUpdatedDate(Util.getCurrentDate());
		classified.setExpiryDate(Util.getClassifiedExpiryDate());
		Long classifiedId = (Long) session.save(classified);
		return classifiedId;
	}

	@Override
	public Integer updateClassified(Classified classified) {
		Session session = sessionFactory.getCurrentSession();
		classified.setUpdatedDate(Util.getCurrentDate());
		classified.setExpiryDate(Util.getClassifiedExpiryDate());
		session.update(classified);
		return null;
	}

	@Override
	public Integer DeleteClassified(Classified classified) {
		classified.setDeleted(Constants.Classified.CLASIFIED_DELETED);
		updateClassified(classified);
		return null;
	}

	@Override
	public Integer DeleteClassified(long classifiedId) {
		Classified classified = getClassified(classifiedId);
		DeleteClassified(classified);
		return null;
	}

	@Override
	public Classified getClassified(long classifiedId) {
		Classified classified = null;
		Session session = sessionFactory.getCurrentSession();
		Object dbObject = session.get(Classified.class, classifiedId);
		if (dbObject instanceof Classified)
			classified = (Classified) dbObject;
		return classified;
	}

	@Override
	public List<Classified> getAllClassifed() {
		return getClassified(0, 0);
	}

	@Override
	public List<Classified> getClassified(int firstResult, int maxResult){
		return 	getClassified(firstResult, maxResult, null);
	}
	
	public List<Classified> getClassified(int firstResult, int maxResult,ClassifiedFilter filters) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Classified where deleted=:deleted order by updatedDate desc");
		query.setParameter("deleted", Constants.Classified.CLASIFIED_NOT_DELETED);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		if (filters != null) {
			setFilters(session,filters);
		}
		List<Classified> classifieds = query.list();
		return classifieds;
	}
	
	
	@Override
	public Long getTotalSize() {
		return getTotalSize(Constants.Classified.TYPE_RENT);
	}

	public Long getTotalSize(int classifiedType) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Classified where deleted=:deleted");
		query.setParameter("deleted", Constants.Classified.CLASIFIED_NOT_DELETED);
		setTypeFilter(session, classifiedType);
		Long result = (Long) query.uniqueResult();
		return result;
	}

	@Override
	public Long getTotalSize(String tags) {
		return getTotalSize(tags, Constants.Classified.TYPE_RENT);
	}
	
	@Override
	public Long getTotalSize(String tags,int classifiedType) {
		if (tags == null) {
			return getTotalSize(classifiedType);
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Classified c join c.tags t where t.name in (:tag) and c.deleted=:deleted");
		query.setParameter("deleted", Constants.Classified.CLASIFIED_NOT_DELETED);
		query.setParameterList("tag", tags.split(","));
		setTypeFilter(session, classifiedType);
		Long result = (Long) query.uniqueResult();
		return result;
	}

	@Override
	public List<Classified> getClassifiedByTags(int firstResult, int maxResults, String tags) {
		if (tags == null) {
			return getClassified(firstResult, maxResults);
		}
		Session session = sessionFactory.getCurrentSession();
		String hql = "select c from Classified c join c.tags t where t.name in (:tag) and c.deleted=:deleted order by c.updatedDate desc";
		// Query query =
		// session.createQuery("from Tag t, Classified c where t.name=:tag and c.deleted=:deleted order by c.updatedDate desc");
		Query query = session.createQuery(hql);
		query.setParameterList("tag", tags.split(","));
		query.setParameter("deleted", Constants.Classified.CLASIFIED_NOT_DELETED);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List<Classified> classifieds = query.list();
		return classifieds;
	}

	@Override
	public List<Classified> getClassifiedByTags(String... tag) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select c from Classified c join c.tags t where t.name in (:tag) and c.deleted=:deleted order by c.updatedDate desc";
		Query query = session.createQuery(hql);
		query.setParameterList("tag", tag);
		query.setParameter("deleted", Constants.Classified.CLASIFIED_NOT_DELETED);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Classified> classifieds = query.list();
		return classifieds;
	}

	@Override
	public List<Classified> getClassifiedByTags(int firstResult, int maxResults, String tags, ClassifiedFilter filters) {

		if (tags == null) {
			return getClassified(firstResult, maxResults,filters);
		}
		Session session = sessionFactory.getCurrentSession();
		String hql = "select c from Classified c join c.tags t where t.name in (:tag) and c.deleted=:deleted order by c.updatedDate desc";
		Query query = session.createQuery(hql);
		query.setParameterList("tag", tags.split(","));
		query.setParameter("deleted", Constants.Classified.CLASIFIED_NOT_DELETED);
		query.setFirstResult(firstResult);
		if (filters != null) {
			setFilters(session,filters);
		}
		query.setMaxResults(maxResults);
		List<Classified> classifieds = query.list();
		return classifieds;
	}

	private void setFilters(Session session, ClassifiedFilter filters) {
		Filter hibernateFilter = session.enableFilter("classifiedType");
		hibernateFilter.setParameter("type", filters.getClassifiedType());
	}

	private void setTypeFilter(Session session, int type) {
		Filter hibernateFilter = session.enableFilter("classifiedType");
		hibernateFilter.setParameter("type", type);
	}
}
