package com.fairdeal.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.fairdeal.Constants;
import com.fairdeal.service.SuggestionService;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;

@ManagedBean(name = "listingSuggestion")
@RequestScoped
public class ListingSuggestion implements Serializable{

	private String suggestions;
	private String searchOption;
	private String finalValue;

	public String getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	public List<String> completeText(String query) {
		LoggerUtil.debug("Finding the Relevant search objects");
		SuggestionService serv = ObjectRepository.getBean(SuggestionService.class);
		List<String> results = serv.getSuggestions(query);
		return results;
	}
	
	public List<String> suggestState(String query) {
		List objectList = Config.getList(Constants.Config.SUGGESTIONS_STATE);
		SuggestionService serv = ObjectRepository.getBean(SuggestionService.class);
		List<String> results = serv.getSuggestions(objectList, query);
		return results;
	}
	
	public List<String> suggestCity(String query) {
		List objectList = Config.getList(Constants.Config.SUGGESTIONS_CITY);
		SuggestionService serv = ObjectRepository.getBean(SuggestionService.class);
		List<String> results = serv.getSuggestions(objectList, query);
		return results;
	}
	
	public String submit() throws IOException{
		String listingType=null;
		String returnString="/views/searchPage.xhtml";
		LoggerUtil.debug(getFinalValue()+" :Calling the submnit button: "+returnString);
		listingType=(searchOption!=null)? searchOption:"1";
		FacesContext.getCurrentInstance().getExternalContext().redirect("searchPage.xhtml?&area="+getFinalValue()+"&listingTypes="+listingType+"&faces-redirect=true");
		return returnString;
	}
	
	public void actionListener(){
		LoggerUtil.debug(getFinalValue()+" :Calling the actionListener button: ");
	}

	@Override
	public String toString() {
		return "ListingSuggestion [suggestions=" + suggestions + ", finalValue=" + finalValue + "]";
	}

	public String getFinalValue() {
		return finalValue;
	}

	public void setFinalValue(String finalValue) {
		this.finalValue = finalValue;
	}

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

}
