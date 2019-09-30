package com.qualtech.cibil.api.request;

import java.io.Serializable;

public class CibilApiRequest  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2919432008065065003L;
	private CibilHeader header;
	private CibilRequestPayload payload;
	public CibilApiRequest() {
		super();
	}
	public CibilApiRequest(CibilHeader header, CibilRequestPayload payload) {
		super();
		this.header = header;
		this.payload = payload;
	}
	public CibilHeader getHeader() {
		return header;
	}
	public void setHeader(CibilHeader header) {
		this.header = header;
	}
	public CibilRequestPayload getPayload() {
		return payload;
	}
	public void setPayload(CibilRequestPayload payload) {
		this.payload = payload;
	}
	
	@Override
	public String toString() {
		return "EquifaxApiRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
}
