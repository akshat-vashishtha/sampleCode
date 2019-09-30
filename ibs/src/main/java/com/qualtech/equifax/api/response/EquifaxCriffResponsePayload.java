package com.qualtech.equifax.api.response;

import java.io.Serializable;
import java.util.Map;

public class EquifaxCriffResponsePayload implements Serializable
{
	 /*
	  * 
	  *
	  */
	private static final long serialVersionUID = -2569829734673314622L;

		Map<String, Object> transaction;
		private String pdfPath;

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

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	

}	



