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
@Table(name = "IB_K_EMPLOYERLOOKUP_REQ")
public class EmployerLookupPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	@Id
	@JsonIgnore
	private String uniqueid;
	@Column
	@JsonIgnore
	private String corelationid;
	@Column
	private String uan;
	@Column
	private String consent;

	public String getUan() {
		return uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
	}

	public String getConsent() {
		return consent;
	}

	public void setConsent(String consent) {
		this.consent = consent;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
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
		return "EmployerLookupPayload [uan=" + uan + ", consent=" + consent
				+ "]";
	}

}
