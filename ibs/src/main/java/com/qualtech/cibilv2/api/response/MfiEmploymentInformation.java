package com.qualtech.cibilv2.api.response;

import java.io.Serializable;

public class MfiEmploymentInformation implements Serializable{

	private static final long serialVersionUID = 7275413173994186509L;
	
	private InformationData informationData;
	private String asOn;
	
	//private String content;
	
	public InformationData getInformationData() {
		return informationData;
	}
	public void setInformationData(InformationData informationData) {
		this.informationData = informationData;
	}
	public String getAsOn() {
		return asOn;
	}
	public void setAsOn(String asOn) {
		this.asOn = asOn;
	}
	
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}
	

}
