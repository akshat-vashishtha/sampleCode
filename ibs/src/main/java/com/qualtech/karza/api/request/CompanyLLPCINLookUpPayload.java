package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table(name="IB_K_LLPCIN_REQ")

public class CompanyLLPCINLookUpPayload implements Serializable{

	private static final long serialVersionUID = -6914504164196462220L;
	@Id
	@JsonIgnore
	@Column(name="UNIQUEID")
	private Long uniqueid;
	@Column(name="CONSENT")
	private String consent;
	@Column(name="COMPANY_NAME")
	private String company_name;
	@JsonIgnore
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
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CompanyLLPCINLookUpPayload [consent=" + consent + ", company_name=" + company_name + "]";
	}
	
	
	
	
}
