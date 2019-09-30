package com.qualtech.equifax.api.response;

public class EvidVoterResponses {

	private EvidVoterRequest evidVoterRequest;
	private EvidVoterResponse evidVoterResponse;
	public EvidVoterRequest getEvidVoterRequest() {
		return evidVoterRequest;
	}
	public void setEvidVoterRequest(EvidVoterRequest evidVoterRequest) {
		this.evidVoterRequest = evidVoterRequest;
	}
	public EvidVoterResponse getEvidVoterResponse() {
		return evidVoterResponse;
	}
	public void setEvidVoterResponse(EvidVoterResponse evidVoterResponse) {
		this.evidVoterResponse = evidVoterResponse;
	}
	@Override
	public String toString() {
		return "EvidVoterResponses [evidVoterRequest=" + evidVoterRequest + ", evidVoterResponse=" + evidVoterResponse
				+ "]";
	}
	
	
	
}
