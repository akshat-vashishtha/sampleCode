package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.ICWAIMembershipPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ICWAIMembershipRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private ICWAIMembershipPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public ICWAIMembershipPayload getPayload() {
		return payload;
	}
	
	public void setPayload(ICWAIMembershipPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "ICWAIMembershipRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
	
}
