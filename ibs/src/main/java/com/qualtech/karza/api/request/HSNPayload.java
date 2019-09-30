package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_HSN_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HSNPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
     private String uniqueid; 
	 private String consent;
	 private String hsCode;
	 private String corelationid;
	 @Column(name="CONSENT")
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	@Column(name="HSCODE")
	public String getHsCode() {
		return hsCode;
	}
	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    @Id
    @JsonIgnore
    @Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@JsonIgnore
	@Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

	@Override
	public String toString() {
		return "HSNPayload [consent=" + consent + ", hsCode=" + hsCode + "]";
	}

	

	 
}
