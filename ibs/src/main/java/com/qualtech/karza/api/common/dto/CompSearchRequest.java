package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.CompSearchPayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompSearchRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private CompSearchPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public CompSearchPayload getPayload() {
		return payload;
	}
	
	
	public void setPayload(CompSearchPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "CompSearchRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
	
}
