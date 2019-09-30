package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.LpgPayload2;

public class LpgRequest2 implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Header header;
	private LpgPayload2 payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}

	public LpgPayload2 getPayload() {
		return payload;
	}
	public void setPayload(LpgPayload2 payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "LpgRequest [header=" + header + ", payload=" + payload + "]";
	}
}
