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
@Table(name = "IB_K_UANLOOKUP_REQ")
public class UanLookupPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	@Id
	@JsonIgnore
	private String uniqueid;
	@Column
	@JsonIgnore
	private String corelationid;
    @Column
	private String mobile;
    @Column
	private String consent;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getConsent() {
		return consent;
	}

	public void setConsent(String consent) {
		this.consent = consent;
	}

	@Override
	public String toString() {
		return "UanLookupPayload [mobile=" + mobile + ", consent=" + consent
				+ "]";
	}

}
