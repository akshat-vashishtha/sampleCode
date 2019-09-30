package com.qualtech.cibilv2.api.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mfi {

private static final long serialVersionUID = 7389134352873725843L;
	
	private String name;
	
	private List<Account> account;
	
	private List<Account> icrsaccount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	public List<Account> getIcrsaccount() {
		return icrsaccount;
	}

	public void setIcrsaccount(List<Account> icrsaccount) {
		this.icrsaccount = icrsaccount;
	}

	
	/*public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getIcrsaccount() {
		return icrsaccount;
	}

	public void setIcrsaccount(Account icrsaccount) {
		this.icrsaccount = icrsaccount;
	}*/
	
	

	

}
