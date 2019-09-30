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
@Table(name="IB_K_LPGIDENTI_REQ")
public class LpgIdentificationPayload implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	@Column(name="UNIQUEID")
	private Long uniqueid;
	@Column(name="INPUT")
	private String input;
	@Column(name="CONSENT")
    private String consent;
	@Column(name="CORELATIONID")
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
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	@Override
	public String toString() {
		return "LpgIdentificationPayload [input=" + input + ", consent=" + consent + "]";
	}

	
}
