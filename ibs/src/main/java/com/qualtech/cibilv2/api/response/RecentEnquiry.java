package com.qualtech.cibilv2.api.response;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class RecentEnquiry implements Serializable{

	private static final long serialVersionUID = 2166077959908518153L;

	private List<RecentEnquiryField> informationData;

	public List<RecentEnquiryField> getInformationData() {
		return informationData;
	}

	public void setInformationData(List<RecentEnquiryField> informationData) {
		this.informationData = informationData;
	}

}
