package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.FacePayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FaceRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private FacePayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public FacePayload getPayload() {
		return payload;
	}
	@Override
	public String toString() {
		return "FaceRequest [header=" + header + ", payload=" + payload + "]";
	}

}
