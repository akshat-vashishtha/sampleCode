package com.qualtech.icici.api.common;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true) 
public class Header implements Serializable{

	private static final long serialVersionUID = 1L;
	private String appId;
	private String correlationId;
	private String msgVersion;
	private String token;

	public Header() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Header(String appId, String correlationId, String msgVersion, String token) {
		super();
		this.appId = appId;
		this.correlationId = correlationId;
		this.msgVersion = msgVersion;
		this.token = token;
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

	public String getMsgVersion() {
		return msgVersion;
	}

	public void setMsgVersion(String msgVersion) {
		this.msgVersion = msgVersion;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Header [appId=" + appId + ", correlationId=" + correlationId + ", msgVersion=" + msgVersion + ", token="
				+ token + "]";
	}

}
