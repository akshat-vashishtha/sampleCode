package com.qualtech.equifax.api.response;

public class EquifaxVerifyIdResponse {

	private VidnsdlResponses vidnsdlresponses;

	public VidnsdlResponses getVidnsdlresponses() {
		return vidnsdlresponses;
	}

	public void setVidnsdlresponses(VidnsdlResponses vidnsdlresponses) {
		this.vidnsdlresponses = vidnsdlresponses;
	}

	@Override
	public String toString() {
		return "EquifaxVerifyIdResponse [vidnsdlresponses=" + vidnsdlresponses + "]";
	}
	
	
	
}
