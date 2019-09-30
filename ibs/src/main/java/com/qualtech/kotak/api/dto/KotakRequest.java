package com.qualtech.kotak.api.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.kotak.api.request.Header;
import com.qualtech.kotak.api.request.KotakPayload;



@JsonIgnoreProperties(ignoreUnknown = true)
public class KotakRequest implements Serializable{
	private static final long serialVersionUID = -1607073637990650355L;
	private Header header;
	private KotakPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public KotakPayload getPayload() {
		return payload;
	}
	
	public void setPayload(KotakPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "KotakRequest [header=" + header + ", payload=" + payload + "]";
	}

	

}
