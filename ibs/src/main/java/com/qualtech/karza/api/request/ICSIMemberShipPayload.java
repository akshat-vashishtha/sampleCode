package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_ICSIMEM_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICSIMemberShipPayload implements Serializable{
	

	private static final long serialVersionUID = -7662582776475423042L;
	 private String uniqueid;
	 private String  membership_no;
	 private String consent;
	 private String cp_no;
	 private String corelationid;
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
	@Column(name="CP_NO")
	public String getCp_no() {
		return cp_no;
	}
	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ICSIMemberShipPayload [membership_no=" + membership_no + ", consent=" + consent + ", cp_no=" + cp_no
				+ "]";
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
