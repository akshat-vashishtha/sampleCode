package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.PanPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PanRequest implements Serializable{
	
	
	private static final long serialVersionUID = -4848339410958768184L;
	private Header header;
	private PanPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public PanPayload getPayload() {
		return payload;
	}
	
	public void setPayload(PanPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "PanRequest [header=" + header + ", payload=" + payload + "]";
	}

}
