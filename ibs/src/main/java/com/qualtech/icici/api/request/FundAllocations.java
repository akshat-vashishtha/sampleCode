package com.qualtech.icici.api.request;

import java.io.Serializable;
import java.util.List;

public class FundAllocations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3214433366516948490L;
	
	private List<Fund> fund;

	public List<Fund> getFund() {
		return fund;
	}

	public void setFund(List<Fund> fund) {
		this.fund = fund;
	}

	@Override
	public String toString() {
		return "FundAllocations [fund=" + fund + "]";
	}
	

}
