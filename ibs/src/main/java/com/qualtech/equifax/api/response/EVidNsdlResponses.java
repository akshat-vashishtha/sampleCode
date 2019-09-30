package com.qualtech.equifax.api.response;

public class EVidNsdlResponses {

	private EvdrNsdlRequest evdrNsdlRequest;
	private EvdrNsdlResponse evdrNsdlResponse;
	
	
	
	public EvdrNsdlRequest getEvdrNsdlRequest() {
		return evdrNsdlRequest;
	}
	public void setEvdrNsdlRequest(EvdrNsdlRequest evdrNsdlRequest) {
		this.evdrNsdlRequest = evdrNsdlRequest;
	}
	public EvdrNsdlResponse getEvdrNsdlResponse() {
		return evdrNsdlResponse;
	}
	public void setEvdrNsdlResponse(EvdrNsdlResponse evdrNsdlResponse) {
		this.evdrNsdlResponse = evdrNsdlResponse;
	}
	
	@Override
	public String toString() {
		return "VidNsdlResponses [evdrNsdlRequest=" + evdrNsdlRequest + ", evdrNsdlResponse=" + evdrNsdlResponse + "]";
	}
	
	
}
