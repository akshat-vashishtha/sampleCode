package com.qualtech.cibilv2.api.request;

import java.io.Serializable;

public class CibilRequest2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8306147893520837140L;
	
	private CibilHeader2 header;
	private CibilRequestPayload2 payload;
	
	public CibilHeader2 getHeader() {
		return header;
	}
	public void setHeader(CibilHeader2 header) {
		this.header = header;
	}
	public CibilRequestPayload2 getPayload() {
		return payload;
	}
	public void setPayload(CibilRequestPayload2 payload) {
		this.payload = payload;
	}
	
	
	
	
}
