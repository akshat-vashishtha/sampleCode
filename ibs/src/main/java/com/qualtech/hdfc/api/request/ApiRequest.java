package com.qualtech.hdfc.api.request;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiRequest {

	private Header header;
	private HdfcRequestPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public HdfcRequestPayload getPayload() {
		return payload;
	}
	public void setPayload(HdfcRequestPayload payload) {
		this.payload = payload;
	}
}
