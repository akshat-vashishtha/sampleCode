package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.GSTAuthenticationPayload;
import com.qualtech.karza.api.request.Header;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GSTAuthenticationRequest implements Serializable{

	private static final long serialVersionUID = 1226325173174245203L;
	private Header header;
	private GSTAuthenticationPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public GSTAuthenticationPayload getPayload() {
		return payload;
	}
	public void setPayload(GSTAuthenticationPayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "GSTAuthenticationRequest [header=" + header + "]";
	}
	
	
}
