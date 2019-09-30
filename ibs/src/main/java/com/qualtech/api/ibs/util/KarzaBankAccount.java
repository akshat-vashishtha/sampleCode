package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaBankAccount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5940402308274098605L;
	private String ifsc;
	private String accountNumber;
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "KarzaBankAccount [ifsc=" + ifsc + ", accountNumber=" + accountNumber + "]";
	}
	
	

}
