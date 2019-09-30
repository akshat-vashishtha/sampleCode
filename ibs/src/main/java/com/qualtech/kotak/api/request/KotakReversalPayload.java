package com.qualtech.kotak.api.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class KotakReversalPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	
	
	private KotakReversal reversal;


	public KotakReversal getReversal() {
		return reversal;
	}


	public void setReversal(KotakReversal reversal) {
		this.reversal = reversal;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "KotakReversalPayload [reversal=" + reversal + "]";
	}
	
	

	
	 
}
