package com.fairdeal.test.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.service.SuggestionService;
import com.fairdeal.test.BaseTest;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;

public class SuggestionServiceTest extends BaseTest{

	@Autowired
	SuggestionService suggestion;
	
	@Test
	public void getSuggestion(){
		LoggerUtil.debug("This is from GetSuggestion");
		String key = "del";
		List<String> collectionList = new LinkedList<String>();
		collectionList.add("Delhi");
		collectionList.add("Faridabad");
		collectionList.add("Haryana");
		collectionList.add("Delhi2");
		collectionList.add("Gurgaon");
		collectionList.add("Delhi");
		collectionList.add("Delhi2d");
		
		List<String> outputList = suggestion.getSuggestions(collectionList,key);
		assertEquals(4, outputList.size());
		assertTrue(outputList.contains("Delhi"));
		assertFalse(outputList.contains("Faridabad"));
		assertTrue(outputList.contains("Delhi2"));
		assertTrue(outputList.contains("Delhi2d"));
	}
	
	
	@Test
	public void getSuggestionFromConfig(){
		LoggerUtil.debug("This is from GetSuggestion");
		String key = "del";
		List collectionList =Config.getList(Constants.Config.SUGGESTIONS_MAIN);
		
		List<String> outputList = suggestion.getSuggestions(collectionList,key);
		assertEquals(4, outputList.size());
	}
}
