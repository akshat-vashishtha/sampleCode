package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class AadharAddressFreetext implements Serializable {
		
	private static final long serialVersionUID = 2791284860088761150L;
	@Column(name="AA_FREETXT_MATCHING_STRATEGY")
	 private String matchingStrategy;
	@Column(name="AA_FREETXT_ADDRESSVALUE")
	 private String addressValue;
	public String getMatchingStrategy() {
		return matchingStrategy;
	}
	public void setMatchingStrategy(String matchingStrategy) {
		this.matchingStrategy = matchingStrategy;
	}
	public String getAddressValue() {
		return addressValue;
	}
	public void setAddressValue(String addressValue) {
		this.addressValue = addressValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Address_Freetext [matchingStrategy=" + matchingStrategy + ", addressValue=" + addressValue + "]";
	}

	 
	
}
