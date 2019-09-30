package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_UDYOGAADHAR_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UdyogAadharPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
     private String uniqueid;
	 private String aadhar;
	 private String uan;
	 private String consent;
	 private String correlationid;
	@Column(name="AADHAR")
	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
    @Column(name="UAN")
	public String getUan() {
		return uan;
	}
	public void setUan(String uan) {
		this.uan = uan;
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
	 
	@Override
	public String toString() {
		return "UdyogAadharPayload [aadhar=" + aadhar + ", uan=" + uan + ", consent=" + consent + "]";
	}

	
}
