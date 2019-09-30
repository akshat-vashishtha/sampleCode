package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.TelephonePayload2;

public class TelephoneRequest2 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private TelephonePayload2 payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public TelephonePayload2 getPayload() {
		return payload;
	}
	public void setPayload(TelephonePayload2 payload) {
		this.payload = payload;
	}
	
	@Override
	public String toString() {
		return "TelephoneRequest [header=" + header + ", payload=" + payload + "]";
	}

}
