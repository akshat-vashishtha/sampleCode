package com.qualtech.api.ibs.util;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxEVDRIbsRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4691488090108840950L;
	private String  amount;
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
