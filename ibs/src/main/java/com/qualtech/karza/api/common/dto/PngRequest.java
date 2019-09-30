package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.PngPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PngRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private PngPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public PngPayload getPayload() {
		return payload;
	}
	
	public void setPayload(PngPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "PngRequest [header=" + header + ", payload=" + payload + "]";
	}
}
