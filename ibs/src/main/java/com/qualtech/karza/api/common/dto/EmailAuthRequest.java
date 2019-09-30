package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.EmailAuthPayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailAuthRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private EmailAuthPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public EmailAuthPayload getPayload() {
		return payload;
	}
	public void setPayload(EmailAuthPayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EmailAuthRequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
