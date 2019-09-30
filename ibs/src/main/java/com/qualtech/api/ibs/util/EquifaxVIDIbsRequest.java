package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class EquifaxVIDIbsRequest implements Serializable {
	
	private String email;
    private String requestType;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	@Override
	public String toString() {
		return "EquifaxVIDIbsRequest [email=" + email + ", requestType=" + requestType + "]";
	}

    
    
}
