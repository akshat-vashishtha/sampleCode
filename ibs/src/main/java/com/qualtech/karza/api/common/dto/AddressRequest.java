package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.AddressPayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private AddressPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public AddressPayload getPayload() {
		return payload;
	}
	
	public void setPayload(AddressPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "Form16Request [header=" + header + ", payload=" + payload + "]";
	}

}
