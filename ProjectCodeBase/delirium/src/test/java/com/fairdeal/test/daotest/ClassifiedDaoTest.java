package com.fairdeal.test.daotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.dao.ClassifiedDao;
import com.fairdeal.entity.Classified;
import com.fairdeal.entity.Location;
import com.fairdeal.entity.Tag;
import com.fairdeal.test.BaseTest;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.TagRepo;

public class ClassifiedDaoTest extends BaseTest {

	private String description = "This is test Descrptiptopn";

	@Autowired
	ClassifiedDao classifiedDao;

	@Autowired
	TagRepo tagRepo;

	@Override
	public void setUp() {
		super.setUp();
		deleteElementTable("CLASSIFIED_TAG");
		deleteElementTable("CLASSIFIED_PARAMS");
		deleteTable("Classified");
	}

	@Test
	public void testInsertClassified() {
		Classified classified = new Classified();
		classified.setDescription(description);
		classified.setCity("India");
		Location location = new Location();
		location.setLatitude(112.22);
		location.setLongitude(112.22);
		classified.setCordinates(location);
		classifiedDao.insertClassified(classified);
	}

	
	@Test
	public void testClassifiedExtraParams() {
		Classified classified = new Classified();
		classified.setDescription(description);
		classified.setCity("India");
		Location location = new Location();
		location.setLatitude(112.22);
		location.setLongitude(112.22);
		classified.setCordinates(location);
		classified.setParam("TestKey","TestValue");
		classified.setParam("ExtraParam1","ExtraParam2");
		classified.setParam("ExtraParam1","ExtraParam3");
		classifiedDao.insertClassified(classified);
		
		Classified newClass = classifiedDao.getClassified(classified.getId());
		assertEquals(classified.getParam("TestKey"), "TestValue");
		assertEquals(classified.getParam("ExtraParam1"), "ExtraParam3");
		
	}
	
	@Test
	public void testFetchClassified() {
		testInsertClassified();
		Classified currentClassified = classifiedDao.getAllClassifed().get(0);
		assertNotNull(currentClassified);
		assertEquals(description, currentClassified.getDescription());

		Location location = currentClassified.getCordinates();
		assertNotNull(location);
	}

	@Test
	public void deleteClassified() {
		testInsertClassified();

		Classified currentClassified = classifiedDao.getAllClassifed().get(0);
		assertNotNull(currentClassified);

		classifiedDao.DeleteClassified(currentClassified);

		int classifiedsSize = classifiedDao.getAllClassifed().size();
		assertEquals(0, classifiedsSize);
	}

	@Test
	public void fetchClassifiedNoClassified() {
		LoggerUtil.debug("Fetching the Classified when no classified exists");
		List classifiedList = classifiedDao.getAllClassifed();
		assertEquals(0, classifiedList.size());

		// Arbitary wront id provided
		Classified classi = classifiedDao.getClassified(100);
		assertNull(classi);
	}

	@Test
	public void registerClassifiedwithTags() {
		Classified classified = new Classified();
		classified.setDescription(description);
		classified.setCity("India");
		Location location = new Location();
		location.setLatitude(112.22);
		location.setLongitude(112.22);
		classified.setCordinates(location);

		List<Tag> tags = new LinkedList<Tag>();
		tags.add(tagRepo.getTag("Delhi"));
		tags.add(tagRepo.getTag("Faridabad"));
		tags.add(tagRepo.getTag("Haryana"));
		classified.setTags(tags);
		classifiedDao.insertClassified(classified);
	}

	//This should be failing, It should be fixed, Its counting the Delhi/Noida as twice
	@Test
	public void fetchClassifiedWithTags() {
		RegisterClassified("Desc1","Delhi","Mumbai");
		RegisterClassified("Desc2","Mumbai");
		RegisterClassified("Desc3","Faridabad","Delhi");
		RegisterClassified("Desc4","Noida","Delhi");
		RegisterClassified("Desc5","Delhi");
		RegisterClassified("Desc6","Mumbai");
		RegisterClassified("Desc7");
		
		List<Classified> allClassifieds = classifiedDao.getClassifiedByTags("DELHI");
		assertEquals(4,allClassifieds.size());
		
		List<Classified> allClassifieds2 = classifiedDao.getClassifiedByTags("DELHI","Noida");
		assertEquals(4,allClassifieds2.size());
	}

	@Test
	public void fetchClassifiedtotalSize() {
		RegisterClassified("Desc1","Delhi","Mumbai");
		RegisterClassified("Desc2","Mumbai");
		RegisterClassified("Desc3","Faridabad","Delhi");
		RegisterClassified("Desc4","Noida","Delhi");
		RegisterClassified("Desc5","Delhi");
		RegisterClassified("Desc6","Mumbai");
		RegisterClassified("Desc7");

		List<Classified> allClassifieds2 = classifiedDao.getClassifiedByTags("DELHI","Noida");
		assertEquals(5,allClassifieds2.size());
		
		long totalSize = classifiedDao.getTotalSize("DELHI");
		long totalSize2 = classifiedDao.getTotalSize("DELHI",Constants.Classified.TYPE_SELL);
		assertEquals(4, totalSize);
		assertEquals(0, totalSize2);
	}
	
	private void RegisterClassified(String desc, String... tags) {
		Classified classified = new Classified();
		classified.setDescription(desc);
		classified.setCity("India");
		Location location = new Location();
		location.setLatitude(112.22);
		location.setLongitude(112.22);
		classified.setCordinates(location);
		classified.setType(Constants.Classified.TYPE_RENT);
		List<Tag> tagList = new LinkedList<Tag>();
		for (String tag : tags) {
			tagList.add(tagRepo.getTag(tag));
		}
		classified.setTags(tagList);
		classifiedDao.insertClassified(classified);
	}

	@Test
	public void getTags() {
		Tag t1 = tagRepo.getTag("PUNEET");
		assertNotNull(t1);
		Tag t2 = tagRepo.getTag("Puneet");
		assertEquals(t1, t2);
		Tag t3 = tagRepo.getTag("PUNEeT");
		assertEquals(t2, t3);
		LoggerUtil.debug(tagRepo.getTag("Puneet"));
		LoggerUtil.debug(tagRepo.getTag("PUNEET"));
		LoggerUtil.debug(tagRepo.getTag("PUNEET"));
		LoggerUtil.debug(tagRepo.getTag("PUNEET"));
		LoggerUtil.debug(tagRepo.getTag("PUNEET"));
	}

}
