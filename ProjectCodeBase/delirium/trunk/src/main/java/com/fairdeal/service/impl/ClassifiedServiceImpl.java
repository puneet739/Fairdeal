package com.fairdeal.service.impl;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.Results;
import com.fairdeal.dao.ClassifiedDao;
import com.fairdeal.dao.UserDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.entity.Classified;
import com.fairdeal.entity.User;
import com.fairdeal.exception.OutOfQuotaException;
import com.fairdeal.filter.ClassifiedFilter;
import com.fairdeal.service.AgentService;
import com.fairdeal.service.ClassifiedService;
import com.fairdeal.service.UserService;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;
import com.fairdeal.util.TagRepo;
import com.fairdeal.util.Util;

public class ClassifiedServiceImpl implements ClassifiedService {

	@Autowired
	ClassifiedDao classifiedDao;

	@Autowired
	TagRepo tagrepo;

	@Autowired
	UserDao userDao;

	@Autowired
	Util util;
	
	@Override
	public Long registerClassified(String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber, String email, String price, String userId, Map<String, InputStream> imagesMap) {
		return registerClassified(shortTitle, description, address, city, state, pincode, contactNumber, email, price, userId, Constants.Classified.TYPE_RENT, imagesMap);
	}

	@Override
	public Long registerClassified(String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber, String email, String price, String userId, int classifiedTyppe, Map<String, InputStream> imagesMap) {
		Classified classified = generateClassifiedEntity(null, shortTitle, description, address, city, state, pincode, contactNumber, email, price, userId, classifiedTyppe, imagesMap);
		User user = userDao.getUser(new Long(userId));
		if (user.isHavingQuota()) {
			long agentId = user.getAgentId();
			if (agentId != 0) {
				Agent agent = ObjectRepository.getBean(AgentService.class).getAgent(agentId);
				int agentCategory = agent.getCategory();
				if (agentCategory == Constants.User.PLATINUM_CATEGORY)
					classified.setCategory(Constants.Classified.PLATINUM_CATEGORY);
				if (agentCategory == Constants.User.SILVER_CATEGORY)
					classified.setCategory(Constants.Classified.SILVER_CATEGORY);
				if (agentCategory == Constants.User.GOLD_CATEGORY)
					classified.setCategory(Constants.Classified.GOLD_CATEGORY);
			}else{
				classified.setCategory(Constants.Classified.GENERAL_CATEGORY);
			}
			classifiedDao.insertClassified(classified);
			user.decreaseQuota();
			userDao.updateUser(user);
			uploadClassifiedImages(classified, imagesMap);
			return classified.getId();
		} else {
			throw new OutOfQuotaException("User cant post classified, As his quota is over. User is " + user);
		}
	}

	private void uploadClassifiedImages(Classified classified, Map<String, InputStream> imagesMap) {
		if (imagesMap == null)
			return;
		String imageUrl1 = Util.saveResizedImageFile(imagesMap.get(Constants.Classified.CLASIFIED_IMAGE1), Config.getString(Constants.Config.CLASSIFIED_IMAGE_STORE_PATH), classified.getId() + "_" + Constants.Classified.CLASIFIED_IMAGE1 + "." + Constants.IMAGE_FORMAT, true);
		String imageUrl2 = Util.saveResizedImageFile(imagesMap.get(Constants.Classified.CLASIFIED_IMAGE2), Config.getString(Constants.Config.CLASSIFIED_IMAGE_STORE_PATH), classified.getId() + "_" + Constants.Classified.CLASIFIED_IMAGE2 + "." + Constants.IMAGE_FORMAT);
		String imageUrl3 = Util.saveResizedImageFile(imagesMap.get(Constants.Classified.CLASIFIED_IMAGE3), Config.getString(Constants.Config.CLASSIFIED_IMAGE_STORE_PATH), classified.getId() + "_" + Constants.Classified.CLASIFIED_IMAGE3 + "." + Constants.IMAGE_FORMAT);
		String imageUrl4 = Util.saveResizedImageFile(imagesMap.get(Constants.Classified.CLASIFIED_IMAGE4), Config.getString(Constants.Config.CLASSIFIED_IMAGE_STORE_PATH), classified.getId() + "_" + Constants.Classified.CLASIFIED_IMAGE4 + "." + Constants.IMAGE_FORMAT);
		if (imageUrl1 != null)
			classified.setImageUrl1(imageUrl1);
		if (imageUrl2 != null)
			classified.setImageUrl2(imageUrl2);
		if (imageUrl3 != null)
			classified.setImageUrl3(imageUrl3);
		if (imageUrl4 != null)
			classified.setImageUrl4(imageUrl4);
		classifiedDao.updateClassified(classified);
	}

	@Override
	public Integer modifyClassified(long id, String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber, String email, String price, Map<String, InputStream> imagesMap) {
		Classified classified = classifiedDao.getClassified(id);
		classified = generateClassifiedEntity(classified, shortTitle, description, address, city, state, pincode, contactNumber, email, price, imagesMap);
		classifiedDao.updateClassified(classified);
		return Results.SUCCESS;
	}

	@Override
	public List<Classified> getClassifieds() {
		return classifiedDao.getAllClassifed();
	}

	@Override
	public Classified getClassified(String id) {
		if (id == null)
			return null;
		Classified classified = classifiedDao.getClassified(Long.parseLong(id));
		return classified;
	}

	public Classified generateClassifiedEntity(Classified classified, String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber, String email, String price, Map<String, InputStream> imagesMap) {
		return generateClassifiedEntity(classified, shortTitle, description, address, city, state, pincode, contactNumber, email, price, null, Constants.Classified.TYPE_RENT, imagesMap);
	}

	public Classified generateClassifiedEntity(Classified classified, String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber, String email, String price, String userId, int classifiedType, Map<String, InputStream> imagesMap) {
		if (classified == null) {
			classified = new Classified();
		}
		classified.setShorttitle(shortTitle);
		classified.setDescription(description);
		classified.setAddress(address);
		classified.setCity(city);
		classified.setState(state);
		classified.setPincode(pincode);
		classified.setContactNumber(contactNumber);
		classified.setPrice(Util.getbigDecimanPrice(price));
		classified.setEmail(email);
		classified.setContactNumber(contactNumber);
		classified.setUserId(userId);
		classified.setType(classifiedType);
		Set<String> currentSet = new HashSet<String>();
		List<String> tags = Util.getTags(description);
		tags.addAll(Util.getTags(shortTitle));
		tags.addAll(Util.getTags(city));
		tags.addAll(Util.getTags(state));

		currentSet.addAll(tags);
		classified.setTags(tagrepo.getTag(new ArrayList(currentSet)));

		return classified;
	}

	@Override
	public List<Classified> getClassifieds(int firstResult, int maxResults, String tags, ClassifiedFilter filters) {
		return classifiedDao.getClassifiedByTags(firstResult, maxResults, tags, filters);
	}

	@Override
	public List<Classified> getClassifieds(int firstResult, int maxResults) {
		return classifiedDao.getClassifiedByTags(firstResult, maxResults, null);
	}

	@Override
	public long getTotalClassifiedCount() {
		long totalSize = classifiedDao.getTotalSize();
		return totalSize;
	}

	@Override
	public long getTotalClassifiedCount(String tags) {
		long totalSize = classifiedDao.getTotalSize(tags);
		return totalSize;
	}

	@Override
	public long getTotalClassifiedCount(String tags, int ClassifiedType) {
		long totalSize = classifiedDao.getTotalSize(tags, ClassifiedType);
		return totalSize;
	}

	@Override
	public Long registerClassified(String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber, String email, String price, Map<String, InputStream> imagesMap) {
		return registerClassified(shortTitle, description, address, city, state, pincode, contactNumber, email, price, null, imagesMap);
	}

	@Override
	public List<Classified> getClassifieds(int firstResult, int maxResults, String tags) {
		return getClassifieds(firstResult, maxResults, tags, null);
	}

	@Override
	public User getClassifiedAgentDetails(String classifiedId, String userId) {
		Classified classified = getClassified(classifiedId);
		User loggedInUser = ObjectRepository.getBean(UserService.class).getUser(Long.parseLong(userId));
		User user = null;
		if (classified.getCategory() == Constants.Classified.GENERAL_CATEGORY) {
			user = ObjectRepository.getBean(UserService.class).getUser(Long.parseLong(classified.getUserId()));
		} else {
			Agent agent = ObjectRepository.getBean(AgentService.class).getAgent(classified.getUserId());
			if (agent != null) {
				user = new User();
				user.setFirstName(agent.getFirstName());
				user.setLastName(agent.getLastName());
				user.setEmailAddress(agent.getEmailAddress());
				user.setPhoneNumber(agent.getEmailAddress());
				user.setImageUrl(agent.getAgentImage());
			}
		}
		LoggerUtil.debug("Agent Details of the classified is provided to user");
		util.classifiedDetailsFetch(classified, loggedInUser);
		return user;
	}

}
