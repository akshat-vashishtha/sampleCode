package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.RCAuthPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RCAuthRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private RCAuthPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public RCAuthPayload getPayload() {
		return payload;
	}
	public void setPayload(RCAuthPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "RCAuthRequest [header=" + header + ", payload=" + payload + "]";
	}

	
}
