package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.ESICIdPayload;
import com.qualtech.karza.api.request.Header;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ESICIdRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private ESICIdPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public ESICIdPayload getPayload() {
		return payload;
	}
	
	public void setPayload(ESICIdPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "ESICIdRequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
