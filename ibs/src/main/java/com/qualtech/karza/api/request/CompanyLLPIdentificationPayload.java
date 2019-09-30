package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
@Table(name="IB_K_LLPIDEN_REQ")
public class CompanyLLPIdentificationPayload implements Serializable{


	private static final long serialVersionUID = -7337395617815027475L;
	private String uniqueid;
	private String consent;
	private String cin;
	private String corelationid;
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
	@Column(name="CIN")
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	@Override
	public String toString() {
		return "CompanyLLPIdentificationPayload [consent=" + consent + ", cin=" + cin + "]";
	}
	@JsonIgnore
	@Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	@JsonIgnore
	@Column(name="UNIQUEID")
	@Id
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	
	
	
	
}
