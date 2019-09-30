package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.ITRAuthPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRAuthRequest implements Serializable{
	private static final long serialVersionUID = -1607073637990650355L;
	private Header header;
	private ITRAuthPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public ITRAuthPayload getPayload() {
		return payload;
	}
	public void setPayload(ITRAuthPayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ITRAuthRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	

}
