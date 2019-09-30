package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_GST_IDENTIFICATION_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GstIdentificationPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
     private String uniqueid;
     private String correlationid;
	 private String gstin;
	 private String consent;
	 private String input;
	 private String state;
	 
	@Column(name="INPUT") 
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="GSTIN")
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	@Column(name="CONSENT")
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "GstIdentificationPayload [gstin=" + gstin + ", consent=" + consent + ", input=" + input + ", state="
				+ state + "]";
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}

}
