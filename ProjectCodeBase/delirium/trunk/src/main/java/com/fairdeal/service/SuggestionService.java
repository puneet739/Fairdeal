package com.fairdeal.service;

import java.util.List;

public interface SuggestionService {

	public List<String> getSuggestions(String key);
	
	public List<String> getSuggestions(List<String> objectList,  String key);
	
}
