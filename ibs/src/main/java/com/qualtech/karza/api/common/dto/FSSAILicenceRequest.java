package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.FSSAILicencePayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FSSAILicenceRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private FSSAILicencePayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public FSSAILicencePayload getPayload() {
		return payload;
	}
	
	public void setPayload(FSSAILicencePayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "FSSAILicenceRequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
