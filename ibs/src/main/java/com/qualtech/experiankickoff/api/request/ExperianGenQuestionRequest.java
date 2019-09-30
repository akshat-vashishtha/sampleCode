package com.qualtech.experiankickoff.api.request;

public class ExperianGenQuestionRequest {

	private Header header;
	private ExperianGenQuestionReqPayload payload;
	
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}


	
	public ExperianGenQuestionReqPayload getPayload() {
		return payload;
	}

	public void setPayload(ExperianGenQuestionReqPayload payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "ExperianRequest [header=" + header + ", payload=" + payload + "]";
	}
}
