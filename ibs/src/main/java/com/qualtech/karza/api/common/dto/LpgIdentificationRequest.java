package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.LpgIdentificationPayload;

public class LpgIdentificationRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Header header;
	private LpgIdentificationPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public LpgIdentificationPayload getPayload() {
		return payload;
	}
	public void setPayload(LpgIdentificationPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "LpgRequest [header=" + header + ", payload=" + payload + "]";
	}
}
