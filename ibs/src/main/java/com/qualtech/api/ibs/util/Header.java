package com.qualtech.api.ibs.util;

import java.util.List;

public class Header {
	private String msgVersion;
	private String appId;
	private String token;
	private String correlationId;
	private String tid;
	private List<String> servicename;
	
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public List<String> getServicename() {
		return servicename;
	}
	public void setServicename(List<String> servicename) {
		this.servicename = servicename;
	}
	@Override
	public String toString() {
		return "Header [msgVersion=" + msgVersion + ", appId=" + appId + ", token=" + token + ", correlationId="
				+ correlationId + ", tid=" + tid + ", servicename=" + servicename + "]";
	}
	
	

}
