package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.GstIdentificationPayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GstIdentificationRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private GstIdentificationPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public GstIdentificationPayload getPayload() {
		return payload;
	}
	
	public void setPayload(GstIdentificationPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "GstIdentificationRequest [header=" + header + ", payload=" + payload + "]";
	}


}
