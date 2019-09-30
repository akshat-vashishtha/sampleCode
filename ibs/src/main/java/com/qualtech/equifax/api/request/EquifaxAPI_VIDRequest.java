package com.qualtech.equifax.api.request;

import java.io.Serializable;

import com.qualtech.equifax.api.common.dto.EquifaxRequestVIDPayload;
import com.qualtech.equifax.api.common.dto.EquifaxUserInfo;

public class EquifaxAPI_VIDRequest implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2404890311282879067L;
	
	EquifaxUserInfo header;
	EquifaxRequestVIDPayload payload;
	
	@Override
	public String toString() {
		return "EquifaxAPI_VIDRequest [header=" + header + ", payload=" + payload + "]";
	}
	public EquifaxUserInfo getHeader() {
		return header;
	}
	public void setHeader(EquifaxUserInfo header) {
		this.header = header;
	}
	public EquifaxRequestVIDPayload getPayload() {
		return payload;
	}
	public void setPayload(EquifaxRequestVIDPayload payload) {
		this.payload = payload;
	}
	

}
