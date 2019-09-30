package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.CAMemberShipAuthPayload;
import com.qualtech.karza.api.request.Header;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CAMemberShipAuthRequest implements Serializable{

	private static final long serialVersionUID = 1226325173174245203L;
	private Header header;
	private CAMemberShipAuthPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public CAMemberShipAuthPayload getPayload() {
		return payload;
	}
	public void setPayload(CAMemberShipAuthPayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CAMemberShipAuthRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
	
}
