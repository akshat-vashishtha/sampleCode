package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.IFSCPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IFSCRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private IFSCPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public IFSCPayload getPayload() {
		return payload;
	}
	
	public void setPayload(IFSCPayload payload) {
		this.payload = payload;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "IFSCRequest [header=" + header + ", payload=" + payload + "]";
	}

	
	
}
