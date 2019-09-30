package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.PassportPayload;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PassportRequest implements Serializable{

	private static final long serialVersionUID = -5210318103233177901L;
	private Header header;
	private PassportPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public PassportPayload getPayload() {
		return payload;
	}
	public void setPayload(PassportPayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PassportRequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
