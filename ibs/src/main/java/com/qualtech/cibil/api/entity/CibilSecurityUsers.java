package com.qualtech.cibil.api.entity;

import java.io.Serializable;

public class CibilSecurityUsers implements Serializable
{
/**
	 * 
	 */
	private static final long serialVersionUID = -9040509130167687815L;
	  
	private Long   userid;	 
	private String username;
	private String password;
	private String role;
	private String enable;
	private int hits;
	private long maximumhits;
	  
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public long getMaximumhits() {
			return maximumhits;
	}
	public void setMaximumhits(long maximumhits) {
		this.maximumhits = maximumhits;
	}
}
