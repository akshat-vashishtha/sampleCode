package com.qualtech.equifax.api.request;

import java.io.Serializable;

import com.qualtech.equifax.api.common.dto.EquifaxRequestEVDRPayload;
import com.qualtech.equifax.api.common.dto.EquifaxUserInfo;

public class EquifaxAPI_EVDRRequest implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5266270493051806136L;
	
	EquifaxUserInfo header;
	EquifaxRequestEVDRPayload payload;
	
	public EquifaxUserInfo getHeader() {
		return header;
	}
	public void setHeader(EquifaxUserInfo header) {
		this.header = header;
	}
	public EquifaxRequestEVDRPayload getPayload() {
		return payload;
	}
	public void setPayload(EquifaxRequestEVDRPayload payload) {
		this.payload = payload;
	}

}
