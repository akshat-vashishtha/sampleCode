package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.DlPayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DlRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private DlPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public DlPayload getPayload() {
		return payload;
	}
	
	public void setPayload(DlPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "DlRequest [header=" + header + ", payload=" + payload + "]";
	}
}
