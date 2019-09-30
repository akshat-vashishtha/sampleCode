package com.qualtech.equifax.api.common.dto;

import java.io.Serializable;
import java.util.List;

import com.qualtech.equifax.api.request.EquifaxVIDRequest;

public class EquifaxRequestVIDPayload implements Serializable
{
	@Override
	public String toString() {
		return "EquifaxRequestVIDPayload [transaction=" + transaction + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 4184936310179926219L;
	List<EquifaxVIDRequest>  transaction;

	public List<EquifaxVIDRequest> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<EquifaxVIDRequest> transaction) {
		this.transaction = transaction;
	}
	
	
	
}
