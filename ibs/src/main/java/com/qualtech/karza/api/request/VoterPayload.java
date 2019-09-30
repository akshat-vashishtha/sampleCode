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
@Table(name="IB_K_VoterRequest_REQ")
public class VoterPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;

	@Id
	@Column(name="UNIQUEID")
	@JsonIgnore
	 private Long uniqueid;
	@Column(name="REG_NO")
	private String epic_no;
	@Column(name="CONSENT") 
	private String consent;
	@Column(name="CORELATIONID")
	@JsonIgnore
	private String corelationid;
	 
	 
	 
	 
	
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
	public String getEpic_no() {
		return epic_no;
	}
	public void setEpic_no(String epic_no) {
		this.epic_no = epic_no;
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

	 
}
