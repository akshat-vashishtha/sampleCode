package com.qualtech.cibilv2.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HeaderInformation implements Serializable{

	private static final long serialVersionUID = -7716869100799668059L;
	
	//@JsonIgnore
	@JsonProperty("informationData")
	private HeaderInformationData headerInformationData;

	public HeaderInformationData getHeaderInformationData() {
		return headerInformationData;
	}

	public void setHeaderInformationData(HeaderInformationData headerInformationData) {
		this.headerInformationData = headerInformationData;
	}
	

}
