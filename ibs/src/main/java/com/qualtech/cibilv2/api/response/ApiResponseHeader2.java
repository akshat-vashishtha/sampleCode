package com.qualtech.cibilv2.api.response;

import java.io.Serializable;

public class ApiResponseHeader2 implements Serializable
{
	private static final long serialVersionUID = 1794255435559192435L;
	String msgVersion = "";
	String appId = "";
	String correlationId = "";
	
	public String getMsgVersion() {
		return msgVersion;
	}

	public void setMsgVersion(String msgVersion) {
		this.msgVersion = msgVersion;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	@Override
	public String toString() {
		return "ApiResponseHeader [msgVersion=" + msgVersion + ", appId=" + appId + ", correlationId=" + correlationId
				+ "]";
	}

}
