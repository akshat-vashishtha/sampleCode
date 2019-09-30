package com.qualtech.finbit.api.request;

import java.io.Serializable;

public class FinbitApiRequest  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2919432008065065003L;
	private FinbitHeader header;
	private FinbitRequestPayload payload;
	public FinbitHeader getHeader() {
		return header;
	}
	public void setHeader(FinbitHeader header) {
		this.header = header;
	}
	public FinbitRequestPayload getPayload() {
		return payload;
	}
	public void setPayload(FinbitRequestPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "CibilApiRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
}
