package com.qualtech.api.ibs.util;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class RequestIBs implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7991647453393862656L;
	
	private Header header;
	private IbsAllServiceRequest payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public IbsAllServiceRequest getPayload() {
		return payload;
	}
	public void setPayload(IbsAllServiceRequest payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "RequestIBs [header=" + header + ", payload=" + payload + "]";
	}
	
	
	
	

}
