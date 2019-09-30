package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import com.qualtech.karza.api.request.LpgPayload;
import com.qualtech.karza.api.request.Header;

public class LpgRequest implements Serializable{

	@Override
	public String toString() {
		return "LpgRequest [header=" + header + ", payload=" + payload + "]";
	}
	private static final long serialVersionUID = 1L;
	
	private Header header;
	private LpgPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public LpgPayload getPayload() {
		return payload;
	}
	public void setPayload(LpgPayload payload) {
		this.payload = payload;
	}
	
}
