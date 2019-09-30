package com.qualtech.equifax.api.request;

import java.io.Serializable;

import com.qualtech.equifax.api.common.dto.EquifaxRequestPayload;
import com.qualtech.equifax.api.common.dto.EquifaxUserInfo;

public class EquifaxApiRequest  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2919432008065065003L;
	private EquifaxUserInfo header;
	private EquifaxRequestPayload payload;
	
	public EquifaxApiRequest() {
		super();
	}

	public EquifaxApiRequest(EquifaxUserInfo header, EquifaxRequestPayload payload) {
		super();
		this.header = header;
		this.payload = payload;
	}
	
	public EquifaxUserInfo getHeader() {
		return header;
	}
	public void setHeader(EquifaxUserInfo header) {
		this.header = header;
	}
	public EquifaxRequestPayload getPayload() {
		return payload;
	}
	public void setPayload(EquifaxRequestPayload payload) {
		this.payload = payload;
	}
	
	@Override
	public String toString() {
		return "EquifaxApiRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
}
