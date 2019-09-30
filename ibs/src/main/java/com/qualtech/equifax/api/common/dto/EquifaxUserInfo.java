package com.qualtech.equifax.api.common.dto;

import java.io.Serializable;

public class EquifaxUserInfo implements Serializable
{

	private static final long serialVersionUID = 5015650153386975994L;
	private String msgVersion = "";
	private String appId = "";
	private String correlationId = "";
	private String userId = "";
	private String password;
	private String rollId = "";
	private String token="";
	
	public String getToken() {
		return token;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRollId() {
		return rollId;
	}
	public void setRollId(String rollId) {
		this.rollId = rollId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "EquifaxUserInfo [msgVersion=" + msgVersion + ", appId=" + appId + ", correlationId=" + correlationId
				+ ", userId=" + userId + ", password=" + password + ", rollId=" + rollId + ", token=" + token + "]";
	}
	
	

}
