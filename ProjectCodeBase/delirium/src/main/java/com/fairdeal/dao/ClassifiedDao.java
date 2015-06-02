package com.fairdeal.dao;

import java.util.List;

import com.fairdeal.entity.Classified;
import com.fairdeal.filter.ClassifiedFilter;

public interface ClassifiedDao extends BaseDao {

	public Long insertClassified(Classified classified);

	public Integer updateClassified(Classified classified);

	public Integer DeleteClassified(Classified classified);

	public Integer DeleteClassified(long classifiedId);
	
	public Classified getClassified(long classifiedId);
	
	public List<Classified> getAllClassifed();
	
	public List<Classified> getClassifiedByTags(String... tag);
	
	public List<Classified> getClassifiedByTags( int firstResult, int maxResults, String tags);
	
	public List<Classified> getClassifiedByTags( int firstResult, int maxResults, String tags, ClassifiedFilter filters);
	
	public List<Classified> getClassified(int firstResult, int maxResult);
	
	public Long getTotalSize(String tags);
	
	public Long getTotalSize(String tags, int classifiedType);

}
