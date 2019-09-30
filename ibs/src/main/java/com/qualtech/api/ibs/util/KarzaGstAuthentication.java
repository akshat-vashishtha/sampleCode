package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaGstAuthentication implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9033185978051262000L;
	private String gstin;

	
	
	public String getGstin() {
		return gstin;
	}



	public void setGstin(String gstin) {
		this.gstin = gstin;
	}



	@Override
	public String toString() {
		return "KarzaGstAuthentication [gstin=" + gstin + "]";
	}
	

}
