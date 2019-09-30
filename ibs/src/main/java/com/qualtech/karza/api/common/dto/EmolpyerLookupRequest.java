package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.EmployerLookupPayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmolpyerLookupRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private EmployerLookupPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public EmployerLookupPayload getPayload() {
		return payload;
	}
	public void setPayload(EmployerLookupPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "EmolpyerLookupRequest [header=" + header + ", payload=" + payload + "]";
	}

}
