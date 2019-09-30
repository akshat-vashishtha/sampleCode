package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_K_Tan_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TanPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	
	@Id
	@JsonIgnore
	private String uniqueId;
	@Column
	@JsonIgnore
	private String correlation_Id;
	@Column
	 private String tan;
	@Column
	 private String consent;
	
	public String getTan() {
		return tan;
	}
	public void setTan(String tan) {
		this.tan = tan;
	}
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getCorrelation_Id() {
		return correlation_Id;
	}
	public void setCorrelation_Id(String correlation_Id) {
		this.correlation_Id = correlation_Id;
	}
	@Override
	public String toString() {
		return "TanPayload [tan=" + tan + ", consent=" + consent + "]";
	}
	
	 
	 
}
