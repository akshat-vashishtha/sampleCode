package com.qualtech.kotak.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.kotak.api.request.KotakReversalHeader;
@JsonIgnoreProperties(ignoreUnknown = true)
public class KotakResponseReversal implements Serializable {

	private static final long serialVersionUID = -749601171233318375L;
	private KotakReversalHeader reversalHeader;
	private KotakReversalDetails reversalDetails;

	public KotakReversalHeader getReversalHeader() {
		return reversalHeader;
	}
	public void setReversalHeader(KotakReversalHeader reversalHeader) {
		this.reversalHeader = reversalHeader;
	}
	public KotakReversalDetails getReversalDetails() {
		return reversalDetails;
	}
	public void setReversalDetails(KotakReversalDetails reversalDetails) {
		this.reversalDetails = reversalDetails;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "KotakResponseReversal [reversalHeader=" + reversalHeader + ", reversalDetails=" + reversalDetails + "]";
	}

	
	

}
