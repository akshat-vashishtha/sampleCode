package com.qualtech.finbit.api.request;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountList {
	
	
	private String accountDetails;
	private String accountUID;
	private String parseStatus;
	private String parseMessage;
	private String dataStatus;
	
	public String getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(String accountDetails) {
		this.accountDetails = accountDetails;
	}
	public String getAccountUID() {
		return accountUID;
	}
	public void setAccountUID(String accountUID) {
		this.accountUID = accountUID;
	}
	public String getParseStatus() {
		return parseStatus;
	}
	public void setParseStatus(String parseStatus) {
		this.parseStatus = parseStatus;
	}
	public String getParseMessage() {
		return parseMessage;
	}
	public void setParseMessage(String parseMessage) {
		this.parseMessage = parseMessage;
	}
	
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	@Override
	public String toString() {
		return "AccountList [accountDetails=" + accountDetails + ", accountUID=" + accountUID + ", parseStatus="
				+ parseStatus + ", parseMessage=" + parseMessage + ", dataStatus=" + dataStatus + "]";
	}
}
