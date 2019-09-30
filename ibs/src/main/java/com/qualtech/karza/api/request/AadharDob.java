package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class AadharDob implements Serializable{

	
	private static final long serialVersionUID = -8923950654981762264L;
	@Column(name="ADRS_DOB_FORMAT")
	private String format;//<YYYYMMDD/YYYY>
	@Column(name="ADRS_DOB_DOBVALUE")
	private String dobValue;
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}

	
	public String getDobValue() {
		return dobValue;
	}
	public void setDobValue(String dobValue) {
		this.dobValue = dobValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AadharDob [format=" + format + ", dobValue=" + dobValue + "]";
	}
	
	
}
