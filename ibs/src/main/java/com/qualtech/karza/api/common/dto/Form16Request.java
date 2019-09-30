package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Form16Payload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Form16Request implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private Form16Payload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Form16Payload getPayload() {
		return payload;
	}
	
	public void setPayload(Form16Payload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "Form16Request [header=" + header + ", payload=" + payload + "]";
	}

}
