package com.fairdeal.cache;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;

public class SuggestionListCache {

	private int maxSize = 500;

	private Map<String, List<String>> suggestionMap;

	public SuggestionListCache() {
		init();
	}

	private void init() {
		suggestionMap = new LinkedHashMap() {
			@Override
			public boolean removeEldestEntry(Map.Entry eldest) {
				return size() > maxSize;
			}
		};
	};
	
	
	public void addSuggestion(String key, List<String> suggestions){
		suggestionMap.put(key, suggestions);
	}
	
	public List<String> getSuggestion(String key){
		List<String> suggestions = suggestionMap.get(key);
		suggestionMap.put(key, suggestions);
		return suggestions;
	}
}
