package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.EPFAuthOTPPayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EPFAuthOTPRequest implements Serializable{
	
	
	private static final long serialVersionUID = -4848339410958768184L;
	private Header header;
	private EPFAuthOTPPayload payload;
	
	public Header getHeader() {
		return header;
	}

	public EPFAuthOTPPayload getPayload() {
		return payload;
	}

	public void setPayload(EPFAuthOTPPayload payload) {
		this.payload = payload;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "EPFAuthOTPRequest [header=" + header + ", payload=" + payload + "]";
	}
	

}
