package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
@Table(name="IB_K_CAMEM_REQ")
public class CAMemberShipAuthPayload implements Serializable{


	private static final long serialVersionUID = -6914504164196462220L;
	private String uniqueid;
	private String consent;
	private String membership_no;
	private String corelationid;
	@Column(name="CONSENT")
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	@Column(name="MEMBERSHIP_NO")
	public String getMembership_no() {
		return membership_no;
	}
	public void setMembership_no(String membership_no) {
		this.membership_no = membership_no;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CAMemberShipAuthPayload [consent=" + consent + ", membership_no=" + membership_no + "]";
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
	@Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	
	
	
}
