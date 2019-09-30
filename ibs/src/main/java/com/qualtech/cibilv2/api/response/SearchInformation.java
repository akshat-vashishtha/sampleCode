package com.qualtech.cibilv2.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchInformation implements Serializable{

	private static final long serialVersionUID = -1877671078371817733L;
	//@JsonIgnore
	@JsonProperty("informationData")
	private SearchInformationData searchInformationData;
//	private String content;

	public SearchInformationData getSearchInformationData() {
		return searchInformationData;
	}

	public void setSearchInformationData(SearchInformationData searchInformationData) {
		this.searchInformationData = searchInformationData;
	}
	

//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}

}
