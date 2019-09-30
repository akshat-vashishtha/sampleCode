package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.NREGAPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NREGARequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private NREGAPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public NREGAPayload getPayload() {
		return payload;
	}
	
	public void setPayload(NREGAPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "NREGARequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
