package com.qualtech.cibilv2.api.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchInformationData {



	private static final long serialVersionUID = 760968693731909064L;
	
	@JsonProperty("field")
	private List<SearchInformationField> searchInformationField;


	public List<SearchInformationField> getSearchInformationField() {
		return searchInformationField;
	}


	public void setSearchInformationField(List<SearchInformationField> searchInformationField) {
		this.searchInformationField = searchInformationField;
	}


	@Override
	public String toString() {
		return "SearchInformationData [searchInformationField=" + searchInformationField + "]";
	}

	
	
}
