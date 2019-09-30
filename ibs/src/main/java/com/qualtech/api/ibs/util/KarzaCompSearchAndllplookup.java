package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaCompSearchAndllplookup implements Serializable{
       /**
	 * 
	 */
	private static final long serialVersionUID = 2005325170961107379L;
	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "KarzaCompSearchAndllplookup [companyName=" + companyName + "]";
	}
       
       
}
