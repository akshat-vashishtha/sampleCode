package com.qualtech.equifax.api.response;

public class EvdrNsdlRequest {

	String source;
	String panNumber;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	@Override
	public String toString() {
		return "EvdrNsdlRequest [source=" + source + ", panNumber=" + panNumber + "]";
	}
	
	
	
}
