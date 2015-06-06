package com.fairdeal.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.fairdeal.entity.Classified;
import com.fairdeal.entity.User;
import com.fairdeal.filter.ClassifiedFilter;

@Transactional
public interface ClassifiedService extends Serializable {

	public Long registerClassified(String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber,String email, String price, String agentId, int classifiedTyppe, Map<String, InputStream> imagesMap);
	
	public Long registerClassified(String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber,String email, String price, String agentId, Map<String, InputStream> imagesMap);
	
	public Long registerClassified(String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber,String email, String price, Map<String, InputStream> imagesMap);
	
	public Integer modifyClassified(long id, String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber,String email, String price, Map<String, InputStream> imagesMap);

	public List<Classified> getClassifieds();
	
	public List<Classified> getClassifieds(int firstResult, int maxResults,String tags, ClassifiedFilter filters );
	
	public List<Classified> getClassifieds(int firstResult, int maxResults,String tags );
	
	public List<Classified> getClassifieds(int firstResult, int maxResults );

	public Classified getClassified(String id);
	
	public long getTotalClassifiedCount();
	
	public long getTotalClassifiedCount(String tags);
	
	public long getTotalClassifiedCount(String tags, int classifiedType);
	
	public User getClassifiedAgentDetails(String classifiedId, String userId);
}
