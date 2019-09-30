package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.NameSimilarityPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NameSimilarityRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private NameSimilarityPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public NameSimilarityPayload getPayload() {
		return payload;
	}
	
	public void setPayload(NameSimilarityPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "NameSimilarityRequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
