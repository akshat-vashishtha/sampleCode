package com.qualtech.finbit.api.request;

import java.io.Serializable;
import java.util.List;

public class FinbitRequestPayload implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8520079869236398364L;
	private List<AccountDetail> accountDetail;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<AccountDetail> getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(List<AccountDetail> accountDetail) {
		this.accountDetail = accountDetail;
	}

	@Override
	public String toString() {
		return "FinbitRequestPayload [accountDetail=" + accountDetail + "]";
	}


	
	

	

	

}
