package com.fairdeal.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.cache.SuggestionListCache;
import com.fairdeal.service.SuggestionService;
import com.fairdeal.util.Config;

public class SuggestionServiceImpl implements SuggestionService{

	@Autowired
	SuggestionListCache suggestionCache;
	
	private int MAX_SUGGESTIONS=5;
	
	@Override
	public List<String> getSuggestions(String key) {
		List objectList = Config.getList(Constants.Config.SUGGESTIONS_MAIN);
		return getSuggestions(objectList, key);
	}

	@Override
	public List<String> getSuggestions(List<String> objectList, String key) {
		key= key.toUpperCase();
		List<String> suggestionList = null;
		suggestionList = suggestionCache.getSuggestion(key);
		if (suggestionList==null){
			suggestionList = new LinkedList<String>();
			for (String currentObject: objectList){
				if (currentObject.toUpperCase().contains(key.toUpperCase())){
					suggestionList.add(currentObject);
					if (suggestionList.size()>MAX_SUGGESTIONS) break;
				};
			}
		}
		suggestionCache.addSuggestion(key, suggestionList);
		return suggestionList;
	}
	
}
