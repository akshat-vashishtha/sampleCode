package com.qualtech.karza.api.common.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.CompanyLLPIdentificationPayload;
import com.qualtech.karza.api.request.Header;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyLLPIdentificationRequest implements Serializable{

	private static final long serialVersionUID = 1892876577798480129L;
	private Header header;
	private CompanyLLPIdentificationPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public CompanyLLPIdentificationPayload getPayload() {
		return payload;
	}
	public void setPayload(CompanyLLPIdentificationPayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CompanyLLPIdentificationRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
	
}
