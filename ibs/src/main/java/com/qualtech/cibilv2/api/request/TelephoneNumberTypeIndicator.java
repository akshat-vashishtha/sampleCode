package com.qualtech.cibilv2.api.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TelephoneNumberTypeIndicator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6323980418526079040L;
	
	private String typeIndicator;
	private String telephoneNumber;
	public String getTypeIndicator() {
		return typeIndicator;
	}
	public void setTypeIndicator(String typeIndicator) {
		this.typeIndicator = typeIndicator;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TelephoneNumberTypeIndicator [typeIndicator=" + typeIndicator + ", telephoneNumber=" + telephoneNumber
				+ "]";
	}
	
	
}
