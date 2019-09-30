package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.CompanyLLPCINLookUpPayload;
import com.qualtech.karza.api.request.Header;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyLLPCINLookUpRequest implements Serializable{


	private static final long serialVersionUID = 8360038631860969041L;
	private Header header;
	private CompanyLLPCINLookUpPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public CompanyLLPCINLookUpPayload getPayload() {
		return payload;
	}
	
	
	public void setPayload(CompanyLLPCINLookUpPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "CompanyLLPCINLookUpRequest [header=" + header + ", payload=" + payload + "]";
	}
	
}
