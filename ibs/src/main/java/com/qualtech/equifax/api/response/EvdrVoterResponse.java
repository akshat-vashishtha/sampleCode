package com.qualtech.equifax.api.response;

public class EvdrVoterResponse {

	String returnCode;
	String voter;
	String title;
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getVoter() {
		return voter;
	}
	public void setVoter(String voter) {
		this.voter = voter;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "EvdrVoterResponse [returnCode=" + returnCode + ", voter=" + voter + ", title=" + title + "]";
	}
	
	
}
