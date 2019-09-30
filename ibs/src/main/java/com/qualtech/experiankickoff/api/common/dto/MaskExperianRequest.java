package com.qualtech.experiankickoff.api.common.dto;

import com.qualtech.experiankickoff.api.request.ExperianMaskReqPayload;
import com.qualtech.experiankickoff.api.request.Header;

public class MaskExperianRequest {
	
	private Header header;
	private ExperianMaskReqPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public ExperianMaskReqPayload getPayload() {
		return payload;
	}
	public void setPayload(ExperianMaskReqPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "ExperianRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
}
