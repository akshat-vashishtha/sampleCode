package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.UdyogAadharPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UdyogAadharRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private UdyogAadharPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public UdyogAadharPayload getPayload() {
		return payload;
	}
	
	public void setPayload(UdyogAadharPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "UdyogAadharRequest [header=" + header + ", payload=" + payload + "]";
	}
	

}
