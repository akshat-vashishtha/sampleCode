package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.IECPayload;
@JsonIgnoreProperties(ignoreUnknown = true)
public class IECRequest implements Serializable{

	private static final long serialVersionUID = 1226325173174245203L;
	private Header header;
	private IECPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public IECPayload getPayload() {
		return payload;
	}
	
	
	public void setPayload(IECPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "IECRequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
