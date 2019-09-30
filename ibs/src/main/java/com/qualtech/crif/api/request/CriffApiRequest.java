package com.qualtech.crif.api.request;

import java.io.Serializable;

import com.qualtech.crif.api.dto.ApiRequestHeader;

public class CriffApiRequest implements Serializable
{
	private static final long serialVersionUID = 7204742726795390733L;
	
	private ApiRequestHeader header;
	private RequestPayload payload;
	
	
	public CriffApiRequest() {
		super();
	}

	public CriffApiRequest(ApiRequestHeader header, RequestPayload payload) {
		super();
		this.header = header;
		this.payload = payload;
	}

	public ApiRequestHeader getHeader() {
		return header;
	}

	public void setHeader(ApiRequestHeader header) {
		this.header = header;
	}

	public RequestPayload getPayload() {
		return payload;
	}

	public void setPayload(RequestPayload payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "ApiRequest [header=" + header + ", payload=" + payload + "]";
	}
}
