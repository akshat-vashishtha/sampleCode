package com.qualtech.equifax.api.response;

public class EvdrVoterRequest {

	String voterId;
	String source;
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Override
	public String toString() {
		return "EvdrVoterRequest [voterId=" + voterId + ", source=" + source + "]";
	}
	
	
}
