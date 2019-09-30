package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.DlPayload2;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DlRequest2 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private DlPayload2 payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public DlPayload2 getPayload() {
		return payload;
	}
	public void setPayload(DlPayload2 payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "DlRequest [header=" + header + ", payload=" + payload + "]";
	}
}
