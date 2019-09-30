package com.qualtech.api.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
@Table(name="IBS_AUTH_VALIDATION")
//@Subselect("select * from IB_AUTH_VALIDATION")
public class AuthValidator {

	@Id
	private String appid;
	private String token;
	private String services;
	private String expirydate;
    @JsonIgnore
	private String status;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	@Override
	public String toString() {
		return "AuthValidator [appid=" + appid + ", token=" + token + ", services=" + services + ", expirydate="
				+ expirydate + ", status=" + status + "]";
	}


}
