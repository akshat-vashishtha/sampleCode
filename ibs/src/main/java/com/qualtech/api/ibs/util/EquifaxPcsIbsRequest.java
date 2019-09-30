package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class EquifaxPcsIbsRequest implements Serializable {
	
	private String requestType;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@Override
	public String toString() {
		return "EquifaxPcsIbsRequest [requestType=" + requestType + "]";
	}
	

}
