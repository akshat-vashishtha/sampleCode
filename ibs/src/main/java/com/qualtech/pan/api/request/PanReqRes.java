package com.qualtech.pan.api.request;

import java.io.Serializable;

public class PanReqRes implements Serializable{
	private static final long serialVersionUID = 1L;
	private String uniqueId;
	private String request;
	private String response;
	private String tag;
	private String description;
	private String corelationid;
	private String appid;
	private String userid;
	private String password;
	private String token;
	private String status;
	private String updatedTime;
	
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getRequest() {
		return request;
	}
	
	
	@Override
	public String toString() {
		return "KarzaReqRes [uniqueId=" + uniqueId + ", request=" + request + ", response=" + response + ", tag=" + tag
				+ ", description=" + description + ", corelationid=" + corelationid + ", appid=" + appid + ", userid="
				+ userid + ", password=" + password + ", token=" + token + ", status=" + status + "]";
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
