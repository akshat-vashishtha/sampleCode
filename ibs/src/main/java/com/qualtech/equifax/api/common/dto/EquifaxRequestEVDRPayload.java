package com.qualtech.equifax.api.common.dto;

import java.io.Serializable;
import java.util.List;

import com.qualtech.equifax.api.request.EquifaxEVDRRequest;


public class EquifaxRequestEVDRPayload  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4551381319897270983L;
	
	List<EquifaxEVDRRequest>  transaction;
	
	public List<EquifaxEVDRRequest> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<EquifaxEVDRRequest> transaction) {
		this.transaction = transaction;
	}

	
}
