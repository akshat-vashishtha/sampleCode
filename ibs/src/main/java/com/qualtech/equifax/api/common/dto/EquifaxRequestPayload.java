package com.qualtech.equifax.api.common.dto;

import java.io.Serializable;
import java.util.List;


import com.qualtech.equifax.api.request.EquifaxRequest;

public class EquifaxRequestPayload implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8520079869236398364L;
	List<EquifaxRequest> transaction;

	public List<EquifaxRequest> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<EquifaxRequest> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "EquifaxRequestPayload [transaction=" + transaction + "]";
	}

	

	

}
