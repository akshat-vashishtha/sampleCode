package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.UanLookupPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UanLookupRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private UanLookupPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}

	public UanLookupPayload getPayload() {
		return payload;
	}
	public void setPayload(UanLookupPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "UanLookupRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
}
