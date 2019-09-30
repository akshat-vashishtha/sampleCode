package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_ICWAIMEMBERSHIP_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICWAIMembershipPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
     private String uniqueid;
	 private String membership_no;
	 private String consent;
	 private String correlationid;
	 @Column(name="MEMBERSHIP_NO")
	public String getMembership_no() {
		return membership_no;
	}
	public void setMembership_no(String membership_no) {
		this.membership_no = membership_no;
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
		return "ICWAIMembershipPayload [membership_no=" + membership_no + ", consent=" + consent + "]";
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
