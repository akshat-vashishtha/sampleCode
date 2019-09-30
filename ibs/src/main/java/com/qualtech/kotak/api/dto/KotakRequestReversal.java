package com.qualtech.kotak.api.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.kotak.api.request.Header;
import com.qualtech.kotak.api.request.KotakReversalPayload;



@JsonIgnoreProperties(ignoreUnknown = true)
public class KotakRequestReversal implements Serializable{
	private static final long serialVersionUID = -1607073637990650355L;
	private Header header;
	private KotakReversalPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public KotakReversalPayload getPayload() {
		return payload;
	}
	public void setPayload(KotakReversalPayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "KotakRequestReversal [header=" + header + ", payload=" + payload + "]";
	}
	
	

}
