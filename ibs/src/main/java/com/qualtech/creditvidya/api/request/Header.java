package com.qualtech.creditvidya.api.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Header implements Serializable{

	private static final long serialVersionUID = 5015650153386975994L;
	private String msgVersion = "";
	private String appId = "";
	private String correlationId = "";
	private String token="";
	
	
	public String getToken() {
		return token;
	}
	@Override
	public String toString() {
		return "Header [msgVersion=" + msgVersion + ", appId=" + appId + ", correlationId=" + correlationId + ", token="
				+ token + "]";
	}
	public void setToken(String token) {
		this.token = token;
	}
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
	
	
}
