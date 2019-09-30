package com.qualtech.karza.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.EPFAuthPassBookPayload;
import com.qualtech.karza.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EPFAuthPassBookRequest implements Serializable{
	
	
	private static final long serialVersionUID = -4848339410958768184L;
	private Header header;
	private EPFAuthPassBookPayload payload;
	
	public Header getHeader() {
		return header;
	}

	public EPFAuthPassBookPayload getPayload() {
		return payload;
	}

	public void setPayload(EPFAuthPassBookPayload payload) {
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
