package com.qualtech.pan.api.request;

import java.io.Serializable;

public class Header implements Serializable
{
	private static final long serialVersionUID = 1794255435559192435L;
	String msgVersion = "";
	String appId = "";
	String correlationId = "";
	String token = "";
	
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Header [msgVersion=" + msgVersion + ", appId=" + appId + ", correlationId=" + correlationId + ", token="
				+ token + "]";
	}
}
