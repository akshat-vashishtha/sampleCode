package com.qualtech.karza.api.response;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompSearchResult implements Serializable{

	private static final long serialVersionUID = -8251784410816419882L;
	
	private List<CompSearchResultData> result;

	

	public List<CompSearchResultData> getResult() {
		return result;
	}

	public void setResult(List<CompSearchResultData> result) {
		this.result = result;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CompSearchResult [result=" + result + "]";
	}


	
	
	
}
