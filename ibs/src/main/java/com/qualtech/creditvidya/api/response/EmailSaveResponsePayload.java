package com.qualtech.creditvidya.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailSaveResponsePayload implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uniqueId;
	

	public String getUniqueId() {
		return uniqueId;
	}


	@Override
	public String toString() {
		return "EmailSaveResponsePayload [uniqueId=" + uniqueId + "]";
	}


	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	

	

}
