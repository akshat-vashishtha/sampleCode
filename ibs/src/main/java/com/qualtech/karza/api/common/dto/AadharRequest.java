package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.AadharPayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AadharRequest implements Serializable{
	private static final long serialVersionUID = -1607073637990650355L;
	private Header header;
	private AadharPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public AadharPayload getPayload() {
		return payload;
	}
	
	public void setPayload(AadharPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "AadharRequest [header=" + header + ", payload=" + payload + "]";
	}
	

}
