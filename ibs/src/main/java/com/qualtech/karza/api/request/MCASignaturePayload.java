package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name="IB_K_MCA_REQ")


public class MCASignaturePayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;

	@Id
	@Column(name="UNIQUEID")
	@JsonIgnore
	private Long uniqueid;
	@Column(name="CIN")
	 private String cin;
	@Column(name="CONSENT")
	 private String consent;
	
	@Column(name="CORELATIONID")
	@JsonIgnore
	 private String corelationid;


	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	

	public Long getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(Long uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

	
	@Override
	public String toString() {
		return "MCASignaturePayload [cin=" + cin + ", consent=" + consent + "]";
	}
	/*@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="QCIB_K_MAIN_SQC") */
	
	
}
