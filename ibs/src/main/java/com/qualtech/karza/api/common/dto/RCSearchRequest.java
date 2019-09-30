package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.RCSearchPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RCSearchRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private RCSearchPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public RCSearchPayload getPayload() {
		return payload;
	}
	
	public void setPayload(RCSearchPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "RCSearchRequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
