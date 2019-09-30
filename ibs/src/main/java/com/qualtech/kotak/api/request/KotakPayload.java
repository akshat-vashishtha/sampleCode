package com.qualtech.kotak.api.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class KotakPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	
	
	private KotakPayment kotakPayment;
	
	public void setKotakPayment(KotakPayment kotakPayment) {
		this.kotakPayment = kotakPayment;
	}
	
	public KotakPayment getKotakPayment() {
		return kotakPayment;
	}

	@Override
	public String toString() {
		return "KotakPayload [kotakPayment=" + kotakPayment + "]";
	}
	
	
	 
}
