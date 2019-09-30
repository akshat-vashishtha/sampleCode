package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class AadharName implements Serializable {
	
	private static final long serialVersionUID = -2370492125757125602L;
	@Column(name="AA_NAME_MATCHING_STRATEGY")
	private String matchingStrategy;
	@Column(name="AA_NAME_NAMEVALUE")
	private String nameValue;
	public String getMatchingStrategy() {
		return matchingStrategy;
	}
	public void setMatchingStrategy(String matchingStrategy) {
		this.matchingStrategy = matchingStrategy;
	}
	public String getNameValue() {
		return nameValue;
	}
	public void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Name [matchingStrategy=" + matchingStrategy + ", nameValue=" + nameValue + "]";
	}
	
	
	
	
}
