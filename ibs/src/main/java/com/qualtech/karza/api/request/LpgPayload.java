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
@Table(name="IB_K_LPG2_REQ")
public class LpgPayload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="UNIQUEID")
	@JsonIgnore
	private Long uniqueid;
	@Column(name="LPG_ID")
	private String lpg_id;
	@Column(name="CONSENT")
    private String consent;
	@JsonIgnore
	@Column(name="CORELATIONID")
	
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
    private String corelationid;
	public String getLpg_id() {
		return lpg_id;
	}
	
	@Override
	public String toString() {
		return "LpgPayload [lpg_id=" + lpg_id + ", consent=" + consent + "]";
	}

	public void setLpg_id(String lpg_id) {
		this.lpg_id = lpg_id;
	}
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}


	
}
