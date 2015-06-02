package com.fairdeal.test.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.entity.Classified;
import com.fairdeal.entity.User;
import com.fairdeal.filter.ClassifiedFilter;
import com.fairdeal.service.ClassifiedService;
import com.fairdeal.service.UserService;
import com.fairdeal.test.BaseTest;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;

public class ClassifiedServiceTest extends BaseTest{

	@Autowired
	private ClassifiedService service;
	
	private String shorttitle="testShortTitle";
	private String description="testShortdesc";
	private String address="testAddress";
	private String city="faridabad";
	private String state="haryana";
	private String pincode="121003";
	private String contactNumber="971161613513";
	private String price="20000000";
	private String email="test@testemail.com";
	private Map<String, InputStream> imagesMap;
	
	String userEmail="puneet739@gmail.com";
	long userId=0;
	
	@Test
	public void registerClassified(){
		long result = service.registerClassified(shorttitle, description, address, city, state, pincode, contactNumber,email, price,""+userId, imagesMap);
		LoggerUtil.debug("Classified is registered");
		assertNotNull(result);
		assertEquals(0, result);
	}
	
	@Test
	public void registerClassifiedFetchDetails(){
		long result = service.registerClassified(shorttitle, description, address, city, state, pincode, contactNumber,email, price,""+userId, imagesMap);
		LoggerUtil.debug("Classified is registered");
		Classified registerdClassified = service.getClassifieds().get(0);
		service.getClassifiedAgentDetails(""+registerdClassified.getId(), ""+userId);
	}
	
	@Test
	public void registerClassifiedForRent(){
		long result = service.registerClassified(shorttitle, description, address, city, state, pincode, contactNumber,email, price,""+userId,Constants.Classified.TYPE_SELL, imagesMap);
		Classified registerdClassified = service.getClassifieds().get(0);
		
		LoggerUtil.debug("Classified is registered");
		assertNotNull(result);
		assertEquals(0, result);
		
		assertEquals(Constants.Classified.TYPE_SELL, registerdClassified.getType());
	}
	
	@Test
	public void testFetchClassifiedWithDifferentType(){
		for (int i=0; i<10; i++){
			service.registerClassified(shorttitle+"XXX"+i, description, address, city, state, pincode, contactNumber,email, price,""+userId,Constants.Classified.TYPE_SELL, imagesMap);
		}
		
		for (int i=0; i<15; i++){
			service.registerClassified(shorttitle+"YYY"+i, description, address, city, state, pincode, contactNumber,email, price,""+userId,Constants.Classified.TYPE_RENT, imagesMap);
		}
		
		ClassifiedFilter filters = new ClassifiedFilter();
		filters.setClassifiedType(Constants.Classified.TYPE_RENT);
		List<Classified> classifiedLists = service.getClassifieds(0, 100, "Faridabad", filters);
		assertEquals(15, classifiedLists.size());
		LoggerUtil.debug("Classified is registered");
		
	}
	
	
	@Test
	public void testfetchClassifiedSizeTags(){
		for (int i=0; i<50; i++){
			long agentId = service.registerClassified(shorttitle+"XXX"+i, description, address, city, state, pincode, contactNumber,email, price,""+userId,Constants.Classified.TYPE_SELL, imagesMap);
		}
		
		long size1 = service.getTotalClassifiedCount("Faridabad");
		assertEquals(50, size1);
		
		
		long size2 = service.getTotalClassifiedCount("Puneet");
		assertEquals(0, size2);
	}
	
	@Test
	public void testfetchClassifiedSizeTagsIsNull(){
		for (int i=0; i<50; i++){
			long agentId = service.registerClassified(shorttitle+i, description+i, address+i, city+i, state+i, pincode+i, contactNumber,email, price,""+userId,Constants.Classified.TYPE_SELL, imagesMap);
		}
		
		long size1 = service.getTotalClassifiedCount("Faridabad");
		assertEquals(50, size1);
		
		
		long size2 = service.getTotalClassifiedCount("Puneet");
		assertEquals(0, size2);
		
		long size3 = service.getTotalClassifiedCount(null);
		assertEquals(50, size3);
	}
	
	@Test
	public void testfetchClassifiedWithNoTags(){
		for (int i=0; i<50; i++){
			long agentId = service.registerClassified(shorttitle+i, description+i, address+i, city+i, state+i, pincode+i, contactNumber,email, price,""+userId,Constants.Classified.TYPE_SELL, imagesMap);
		}
		
		List l = service.getClassifieds(0, 10, "Faridabad,Haryana");
		assertEquals(l.size(), 10);
		
		List l2 = service.getClassifieds(0, 10);
		assertEquals(l2.size(), 10);
		
		
		List l3 = service.getClassifieds(0, 20);
		assertEquals(l3.size(), 20);
	}
	
	
	
	@Override
	public void setUp(){
		deleteElementTable("CLASSIFIED_TAG");
		deleteElementTable("CLASSIFIED_PARAMS");
		deleteElementTable("USERROLES");
		deleteTable("Classified");
		deleteTable("User");
		deleteTable("Agent");
		
		String userEmail="puneet739@gmail.com";
		userId = registerUser(userEmail);
		
		imagesMap = new HashMap<String, InputStream>();
		try {
			File file = new File("mainImage.jpg");
			imagesMap.put(Constants.Classified.CLASIFIED_IMAGE1, new FileInputStream(file));
			imagesMap.put(Constants.Classified.CLASIFIED_IMAGE2, new FileInputStream(file));
			imagesMap.put(Constants.Classified.CLASIFIED_IMAGE3, new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
