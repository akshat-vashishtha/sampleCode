package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.ShopEstablishmentPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopEstablishmentRequest implements Serializable{
	
	
	private static final long serialVersionUID = 361268851561277482L;
	private Header header;
	private ShopEstablishmentPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public ShopEstablishmentPayload getPayload() {
		return payload;
	}
	public void setPayload(ShopEstablishmentPayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ShopEstablishmentRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	

}
