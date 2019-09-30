package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.ICSIMemberShipPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ICSIMemberShipRequest implements Serializable{
	
	
	private static final long serialVersionUID = -4848339410958768184L;
	private Header header;
	private ICSIMemberShipPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public ICSIMemberShipPayload getPayload() {
		return payload;
	}
	public void setPayload(ICSIMemberShipPayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ICSIMemberShipRequest [header=" + header + ", payload=" + payload + "]";
	}
	

}
