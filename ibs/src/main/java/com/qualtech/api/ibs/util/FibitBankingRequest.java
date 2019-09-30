package com.qualtech.api.ibs.util;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class FibitBankingRequest {
	private List<FibitRequest> accountDetail;

	public List<FibitRequest> getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(List<FibitRequest> accountDetail) {
		this.accountDetail = accountDetail;
	}

	@Override
	public String toString() {
		return "FibitBankingRequest [accountDetail=" + accountDetail + "]";
	}
	
	
	
}
