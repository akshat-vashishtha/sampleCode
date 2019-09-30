package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.TelephonePayload;

public class TelephoneRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private TelephonePayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public TelephonePayload getPayload() {
		return payload;
	}
	@Override
	public String toString() {
		return "TelephoneRequest [header=" + header + ", payload=" + payload + "]";
	}
	public void setPayload(TelephonePayload payload) {
		this.payload = payload;
	}
	

}
