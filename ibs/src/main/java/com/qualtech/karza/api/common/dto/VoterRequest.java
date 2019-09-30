package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.VoterPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VoterRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private VoterPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setPayload(VoterPayload payload) {
		this.payload = payload;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public VoterPayload getPayload() {
		return payload;
	}
	
	@Override
	public String toString() {
		return "VoterRequest [header=" + header + ", payload=" + payload + "]";
	}
}
