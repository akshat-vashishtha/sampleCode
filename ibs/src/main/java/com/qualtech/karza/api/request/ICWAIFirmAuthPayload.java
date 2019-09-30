package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
@Entity
@Table(name="IB_K_ICWAIFIRMAUTH_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICWAIFirmAuthPayload implements Serializable{
	

	private static final long serialVersionUID = 1967711269513368218L;
    private String uniqueid;
    private String corelationid;
	private String  consent;
	private String reg_no;
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
	@Column(name="REG_NO")
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	@Override
	public String toString() {
		return "ICWAIFirmAuthPayload [consent=" + consent + ", reg_no=" + reg_no + "]";
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
