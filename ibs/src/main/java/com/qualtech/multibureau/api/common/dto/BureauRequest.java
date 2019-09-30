package com.qualtech.multibureau.api.common.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.multibureau.api.request.BureauPayload;
import com.qualtech.multibureau.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BureauRequest implements Serializable{

	private static final long serialVersionUID = -1273372589904178779L;

	private Header header;
	
	private BureauPayload payload;

	public Header getHeader() {
		return header;
	}


	public void setHeader(Header header) {
		this.header = header;
	}


	public BureauPayload getPayload() {
		return payload;
	}


	public void setPayload(BureauPayload payload) {
		this.payload = payload;
	}


	@Override
	public String toString() {
		return "BureauRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
}
