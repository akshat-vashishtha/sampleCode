package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.HSNPayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HSNRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private HSNPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public HSNPayload getPayload() {
		return payload;
	}
	public void setPayload(HSNPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "HSNRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
}
