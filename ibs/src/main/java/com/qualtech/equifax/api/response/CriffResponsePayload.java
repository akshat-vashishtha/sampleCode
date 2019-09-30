package com.qualtech.equifax.api.response;

import java.io.Serializable;
import java.util.Map;

public class CriffResponsePayload implements Serializable
{
	private static final long serialVersionUID = -3757129230570633891L;
	
	Map<String, Object> transaction;

	public Map<String, Object> getTransaction() {
		return transaction;
	}

	public void setTransaction(Map<String, Object> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponsePayload [transaction=");
		builder.append(transaction);
		builder.append("]");
		return builder.toString();
	}
}
