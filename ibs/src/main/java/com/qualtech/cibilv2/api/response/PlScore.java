package com.qualtech.cibilv2.api.response;

import java.io.Serializable;

public class PlScore implements Serializable{

	private static final long serialVersionUID = -4744642630096013838L;
	
	private InformationData informationData;
	private String content;
	
	public InformationData getInformationData() {
		return informationData;
	}
	public void setInformationData(InformationData informationData) {
		this.informationData = informationData;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
