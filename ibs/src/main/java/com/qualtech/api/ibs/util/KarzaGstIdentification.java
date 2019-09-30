package com.qualtech.api.ibs.util;

public class KarzaGstIdentification {
	
	
	private String gstin;
	private String input;
	
	
	

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	@Override
	public String toString() {
		return "KarzaGstIdentification [gstin=" + gstin + "]";
	}
	
	

}
