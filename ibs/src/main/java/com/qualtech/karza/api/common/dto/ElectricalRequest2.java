package com.qualtech.karza.api.common.dto;


import java.io.Serializable;

import com.qualtech.karza.api.request.ElectricalPayload2;
import com.qualtech.karza.api.request.Header;

public class ElectricalRequest2 implements Serializable{
	private static final long serialVersionUID = 1L;
	private Header header;
	private ElectricalPayload2 payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public ElectricalPayload2 getPayload() {
		return payload;
	}
	public void setPayload(ElectricalPayload2 payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "DlRequest [header=" + header + ", payload=" + payload + "]";
	}
}
