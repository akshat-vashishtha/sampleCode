package com.qualtech.cibilv2.api.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HeaderInformationData {
    @JsonProperty("field")
	private List<HeaderInformationField> headerInformationfield;

	public List<HeaderInformationField> getHeaderInformationfield() {
		return headerInformationfield;
	}

	public void setHeaderInformationfield(List<HeaderInformationField> headerInformationfield) {
		this.headerInformationfield = headerInformationfield;
	}
	
	
	
}

