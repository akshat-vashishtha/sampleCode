package com.qualtech.experiankickoff.api.request;

public class AuthExperianRequest {


	private Header header;
	private AuthExperianRequestPayload payload;
	
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public AuthExperianRequestPayload getPayload() {
		return payload;
	}

	public void setPayload(AuthExperianRequestPayload payload) {
		this.payload = payload;
	}

	
	@Override
	public String toString() {
		return "ExperianRequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
