package com.qualtech.cibil.api.request;

import java.io.Serializable;
import java.util.List;


import com.qualtech.cibil.api.request.CibilRequest;

public class CibilRequestPayload implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8520079869236398364L;
	private List<CibilRequest> transaction;

	public List<CibilRequest> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<CibilRequest> transaction) {
		this.transaction = transaction;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CibilRequestPayload [transaction=" + transaction + "]";
	}

	

	

	

}
