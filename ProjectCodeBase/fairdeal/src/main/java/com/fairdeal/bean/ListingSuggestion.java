package com.fairdeal.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.fairdeal.utility.LoggerUtil;

@ManagedBean(name = "listingSuggestion")
@RequestScoped
public class ListingSuggestion implements Serializable{

	private String suggestions;
	private String finalValue;

	public String getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	public List<String> completeText(String query) {
		LoggerUtil.debug("Finding the Relevant search objects");
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			results.add(query + i);
		}
		return results;
	}
	
	public String submit() throws IOException{
		String returnString="/views/searchPage.xhtml";
		LoggerUtil.debug(getFinalValue()+" :Calling the submnit button: "+returnString);
		FacesContext.getCurrentInstance().getExternalContext().redirect("searchPage.xhtml?area="+getFinalValue());
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

}
