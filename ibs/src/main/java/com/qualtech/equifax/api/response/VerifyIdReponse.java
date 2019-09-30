package com.qualtech.equifax.api.response;

public class VerifyIdReponse {

	private EVidNsdlResponses vidNsdlResponses;
	private EvidVoterResponses evidVoterResponses;
	public EVidNsdlResponses getVidNsdlResponses() {
		return vidNsdlResponses;
	}
	public void setVidNsdlResponses(EVidNsdlResponses vidNsdlResponses) {
		this.vidNsdlResponses = vidNsdlResponses;
	}
	public EvidVoterResponses getEvidVoterResponses() {
		return evidVoterResponses;
	}
	public void setEvidVoterResponses(EvidVoterResponses evidVoterResponses) {
		this.evidVoterResponses = evidVoterResponses;
	}
	@Override
	public String toString() {
		return "VerifyIdReponse [vidNsdlResponses=" + vidNsdlResponses + ", evidVoterResponses=" + evidVoterResponses
				+ "]";
	}
	
	
}
