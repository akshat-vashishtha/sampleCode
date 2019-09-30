package com.qualtech.crif.api.request;

import java.io.Serializable;
import java.util.List;

public class RequestPayload implements Serializable
{
	private static final long serialVersionUID = 3007147797603226464L;
	private List<CrifRequest> transaction;

	public List<CrifRequest> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<CrifRequest> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "RequestPayload [transaction=" + transaction + "]";
	}

}
