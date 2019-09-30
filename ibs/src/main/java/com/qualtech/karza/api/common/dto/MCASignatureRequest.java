package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.MCASignaturePayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MCASignatureRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private MCASignaturePayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public MCASignaturePayload getPayload() {
		return payload;
	}
	
	
	public void setPayload(MCASignaturePayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "MCASignatureRequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
