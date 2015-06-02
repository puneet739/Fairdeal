package com.fairdeal.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.entity.Tag;

/**
 * This will be providing the tags for classified
 * @author pbehl1
 *
 */
public class TagRepo {

	private Map<String, Tag> tagRepo = new HashMap<String, Tag>();

	@Autowired
	private SessionFactory sessionFactory;
	
	public Tag getTag(String name) {
		name = name.toUpperCase();
		Tag tags = null;
		tags = getTagFromMemory(name); 
		return tags;
	}

	public List<Tag> getTag(List<String> tags) {
		List<Tag> outputTags = new LinkedList<Tag>();
		for (String tag : tags){
			outputTags.add(getTag(tag));
		}
		return outputTags;
	}
	
	private void addTagRepo(String tag) {
		tagRepo.put(tag, new Tag(tag));
	}
	
	private Tag getTagFromDB(String tag) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Tag where name=:name");
		query.setParameter("name", tag);
		Tag dbTag = (Tag)query.uniqueResult();
		if (dbTag==null){
			dbTag = saveTag(tag);
		}
		tagRepo.put(tag,dbTag);
		return dbTag;
	}
	
	private Tag getTagFromMemory(String tag) {
		Tag tags;
		tags = tagRepo.get(tag);
		if (tags == null) {
			tags = getTagFromDB(tag);
		}
		return tags;
	}

	
	private Tag saveTag(String name) {
		Tag tag = new Tag(name);
		Session session = sessionFactory.getCurrentSession();
		int tagId = (Integer)session.save(tag);
		tag.setTagId(tagId);
		return tag;
	}
}
